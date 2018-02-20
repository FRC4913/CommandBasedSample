package org.usfirst.frc.team4913.robot.commands;

import static org.usfirst.frc.team4913.robot.Robot.driveSubsystem;
import static org.usfirst.frc.team4913.robot.Robot.intaker;
import static org.usfirst.frc.team4913.robot.Robot.elevator;

import org.usfirst.frc.team4913.robot.Robot;
import edu.wpi.first.wpilibj.MotorSafetyHelper;
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
	private static final double TURN_90_TIME = 1.0;
	private static final double POSITION_TIME = 3.0;
	private static final double APPROACH_TIME = 3.0;
	private static final double VISION_TIME = 3.0;
	private static final double DELIVER_CUBE = 3.0;
	private static final double GO_STRAIGHT = 5.0;

	private boolean isFinished = false;
	private Robot.TURN direction;
	private boolean deliverCube = true; // default true
	private boolean useVision = false; // default true

	private double initFwdTime = INIT_FWD_TIME;
	private double turn1stTime = initFwdTime + TURN_90_TIME;
	private double positionTime = turn1stTime + POSITION_TIME;
	private double turn2ndTime = positionTime + TURN_90_TIME;
	private double approachTime = turn2ndTime + APPROACH_TIME;
	private double visionMiddleTime = approachTime + VISION_TIME;
	private double visionSidesTime = GO_STRAIGHT + VISION_TIME;

	public AutonomousDrive(Robot.TURN direction) {
		this(direction, true, true);
	}

	public AutonomousDrive(Robot.TURN direction, boolean deliverCube) {
		this(direction, deliverCube, false);
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
		timer.reset();
		timer.start();
	}

	protected void execute() {
		double timerVal = timer.get();

		// testing
		System.out.println("Direction: " + this.direction);
		System.out.println("UseVision: " + this.useVision);
		System.out.println("DeliverCube: " + this.deliverCube);

		if (direction == Robot.TURN.STRAIGHT) {
			startFromTwoSides(timerVal);
		} else {
			startInTheMiddle(timerVal);
		}

		// if (this.useVision) {
		// executeVision(timerVal);
		// }

		if (this.deliverCube) {
			deliverCube(timerVal);
		}
	}

	private void startInTheMiddle(double timerVal) {
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

	}

	private void startFromTwoSides(double timerVal) {
		if (timerVal < GO_STRAIGHT)
			driveSubsystem.arcadeDrive(-1.0, 0.0);
	}

	private void executeVision(double timerVal) {
		if (this.direction == Robot.TURN.STRAIGHT) {
			if (timerVal < visionSidesTime)
				driveSubsystem.autoDrive();
		} else {
			if (timerVal < visionMiddleTime)
				driveSubsystem.autoDrive();
		}
	}

	private void deliverCube(double timerVal) {
		if (this.direction == Robot.TURN.STRAIGHT) {
			if (!this.useVision) {
				if (timerVal >= visionSidesTime && timerVal < visionSidesTime + DELIVER_CUBE)
					elevator.up();
			} else {
				if (timerVal >= GO_STRAIGHT && timerVal < GO_STRAIGHT + DELIVER_CUBE)
					elevator.up();
			}
		} else {
			if (!this.useVision) {
				if (timerVal >= visionMiddleTime && timerVal < visionMiddleTime + DELIVER_CUBE)
					elevator.up();
			} else {
				if (timerVal >= approachTime && timerVal < approachTime + DELIVER_CUBE)
					elevator.up();
			}
		}
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
