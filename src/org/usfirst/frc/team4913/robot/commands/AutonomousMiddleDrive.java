package org.usfirst.frc.team4913.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

import static org.usfirst.frc.team4913.robot.Robot.*;

import org.usfirst.frc.team4913.robot.Robot;

/**
 *
 */
public class AutonomousMiddleDrive extends Command {

	Timer timer = new Timer();

	private boolean isFinished = false;

	private TURN direction;
	private boolean deliverCube = false;
	private boolean useVision = false;

	private static final double INIT_FWD_TIME = 1.0;
	private static final double TURN_90_TIME = 1.0;
	private static final double POSITION_TIME = 3.0;
	private static final double APPROACH_TIME = 3.0;
	private static final double VISION_TIME = 3.0;
	private static final double DELIVER_CUBE = 3.0;
	private static final double GO_STRAIGHT = 5.0;
	private double initFwdTime = INIT_FWD_TIME;
	private double turn1stTime = initFwdTime + TURN_90_TIME;
	private double positionTime = turn1stTime + POSITION_TIME;
	private double turn2ndTime = positionTime + TURN_90_TIME;
	private double approachTime = turn2ndTime + APPROACH_TIME;
	private double visionMiddleTime = approachTime + VISION_TIME;
	private double visionSidesTime = GO_STRAIGHT + VISION_TIME;

	public AutonomousMiddleDrive(TURN direction, boolean deliverCube, boolean useVision) {
		requires(driveSubsystem);
		requires(intaker);
		this.direction = direction;
		this.deliverCube = deliverCube;
		this.useVision = useVision;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		timer.reset();
		timer.start();
	}

	protected void execute() {
		double timerVal = timer.get();

		// testing
		System.out.println("Direction: " + this.direction);
		System.out.println("UseVision: " + this.useVision);
		System.out.println("DeliverCube: " + this.deliverCube);

		if (timerVal < initFwdTime)
			driveSubsystem.arcadeDrive(-1.0, 0.0); // forward
		else if (timerVal >= initFwdTime && timerVal < turn1stTime) { // initial turn
			if (direction == Robot.TURN.LEFT) {
				driveSubsystem.arcadeDrive(0.0, -1.0);// turn left
			} else {
				driveSubsystem.arcadeDrive(0.0, 1.0);// turn right
			}
		} else if (timerVal >= turn1stTime && timerVal < positionTime) {
			driveSubsystem.arcadeDrive(-1.0, 0.0); // 2nd forward
		} else if (positionTime >= positionTime && timerVal < turn2ndTime) { // turn back facing switch
			if (direction == Robot.TURN.LEFT) {
				driveSubsystem.arcadeDrive(0.0, 1.0);// turn right
			} else {
				driveSubsystem.arcadeDrive(0.0, -1.0); // turn left
			}
		} else if (timerVal >= turn2ndTime && timerVal < approachTime)
			driveSubsystem.arcadeDrive(-1.0, 0.0); // 3nd forward
		else
			intaker.releaseBlock();
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