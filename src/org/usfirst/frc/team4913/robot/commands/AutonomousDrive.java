package org.usfirst.frc.team4913.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4913.robot.Robot;
import org.usfirst.frc.team4913.robot.subsystems.Intaker;

import static org.usfirst.frc.team4913.robot.Robot.driveSubsystem;

//import org.usfirst.frc.team4913.robot.TimedAutonomousTurnLeftDeliverCube;
//import org.usfirst.frc.team4913.robot.TimedAutonomousTurnLeftDeliverCube;

/**
 *
 */
public class AutonomousDrive extends Command {

	Timer timer = new Timer();
	private boolean isFinished = false;
	private Robot.TURN direction;

	public AutonomousDrive(Robot.TURN direction) {
		requires(Robot.driveSubsystem);
		requires(Robot.intaker);
		this.direction = direction;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		timer.reset();
		timer.start();
	}

	protected void execute() {
		double timerVal = timer.get();
		if(timerVal < 1) {
			driveSubsystem.arcadeDrive(-1.0, 0.0); // forward
		}
		if(timerVal >= 1 && timerVal < 2) { // initial turn
			if (direction == Robot.TURN.LEFT)
				driveSubsystem.arcadeDrive(0.0, -1.0); // turn left
			else if (direction == Robot.TURN.RIGHT)
				driveSubsystem.arcadeDrive(0.0, 1.0); // turn right
		}
		if(timerVal >= 2 && timerVal < 4) {
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

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return isFinished;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.intaker.stop();
		driveSubsystem.stopMotor();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
