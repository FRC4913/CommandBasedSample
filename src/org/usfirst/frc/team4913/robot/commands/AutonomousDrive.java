package org.usfirst.frc.team4913.robot.commands;

import static org.usfirst.frc.team4913.robot.Robot.driveSubsystem;
import static org.usfirst.frc.team4913.robot.Robot.intaker;

import org.usfirst.frc.team4913.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

//import org.usfirst.frc.team4913.robot.TimedAutonomousTurnLeftDeliverCube;
//import org.usfirst.frc.team4913.robot.TimedAutonomousTurnLeftDeliverCube;

/**
 *
 */
public class AutonomousDrive extends Command {

	Timer timer = new Timer();

	private static final double INIT_FWD_TIME = 1.0;

	// time it takes to turn 90 degrees
	private static final double TURN_90_TIME = 1.0;

	// time for positioning in front of switch
	private static final double POSITION_TIME = 3.0;

	// time for positioning in front of switch
	private static final double APPROACH_TIME = 3.0;

	private boolean isFinished = false;
	private Robot.TURN direction;
	private boolean deliverCube; // default true
	private boolean useVision; // default true

	private double initFwdTime;
	private double turn1stTime;
	private double positionTime;
	private double turn2ndTime;
	private double approachTime;
	
	public AutonomousDrive(Robot.TURN direction) {
		this(direction, true, true);
	}

	public AutonomousDrive(Robot.TURN direction, boolean deliverCube) {
		this(direction, deliverCube, true);
	}

	public AutonomousDrive(Robot.TURN direction, boolean deliverCube, boolean useVision) {
		requires(driveSubsystem);
		requires(intaker);
		this.direction = direction;
		this.deliverCube = deliverCube;
		this.useVision = useVision;		
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		initFwdTime = INIT_FWD_TIME;
		turn1stTime = initFwdTime + TURN_90_TIME;
		positionTime = turn1stTime + POSITION_TIME;
		turn2ndTime = positionTime + TURN_90_TIME;
		approachTime = turn2ndTime + APPROACH_TIME;

		timer.reset();
		timer.start();
	}

	protected void execute() {
		double timerVal = timer.get();
		if (this.useVision) {
			executeWithVision(timerVal);
		}
		else {
			executeWithoutVision(timerVal);
		}
	}


	private void executeWithoutVision(double timerVal) {
		if(timerVal < initFwdTime) {
			driveSubsystem.arcadeDrive(-1.0, 0.0); // forward
		}
		if(timerVal >= initFwdTime && timerVal < turn1stTime) { // initial turn
			if (direction == Robot.TURN.LEFT)
				driveSubsystem.arcadeDrive(0.0, -1.0); // turn left
			else if (direction == Robot.TURN.RIGHT)
				driveSubsystem.arcadeDrive(0.0, 1.0); // turn right
			else
				driveSubsystem.arcadeDrive(-1.0, 0.0);
		}
		if(timerVal >= turn1stTime && timerVal < positionTime) {
			if (direction == Robot.TURN.LEFT || direction == Robot.TURN.RIGHT)
			driveSubsystem.arcadeDrive(-1.0, 0.0); // 2nd forward
		}
		if(timerVal >= 4 && timerVal < 5) { // turn back facing switch
			if (direction == Robot.TURN.LEFT)
				driveSubsystem.arcadeDrive(0.0, 1.0); // turn right
			else if (direction == Robot.TURN.RIGHT)
				driveSubsystem.arcadeDrive(0.0, -1.0); // turn left
			else if (direction == Robot.TURN.STRAIGHT) {
				driveSubsystem.arcadeDrive(-1.0, 0.0); // keep going straight
			}
		}
		if(timerVal >= 5) { //&& timerVal < 13) {
			driveSubsystem.autoDrive();
		}
//		if(timerVal >= 13 && timerVal < 15) {
//			grabberSubsystem.releaseBlock();
//		}
//		if(timerVal > 15) {
//		isFinished = true;
//		}
		
	}
	
	private void executeWithVision(double timerVal) {
		
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return isFinished;
	}

	// Called once after isFinished returns true
	protected void end() {
		intaker.stop();
		driveSubsystem.stopMotor();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
