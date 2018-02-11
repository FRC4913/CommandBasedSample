package org.usfirst.frc.team4913.robot.commands;

import org.usfirst.frc.team4913.robot.Robot;
//import org.usfirst.frc.team4913.robot.TimedAutonomousTurnLeftDeliverCube;
//import org.usfirst.frc.team4913.robot.TimedAutonomousTurnLeftDeliverCube;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TimedAutonomousTurnLeftDeliverCube extends Command {

	Timer timer = new Timer();
	private boolean isFinished = false;

	public TimedAutonomousTurnLeftDeliverCube() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.driveSubsystem);
		requires(Robot.grabbersbusytem);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		timer.reset();
		timer.start();
	}

	protected void execute() {
		if (timer.get() < 1) {
			Robot.driveSubsystem.acradeDrive(-1.0, 0.0); // forward
		} else if (timer.get() >= 1 && timer.get() < 2) {
			Robot.driveSubsystem.acradeDrive(0.0, -0.5); // turn left
		} else if (timer.get() >= 2 && timer.get() < 3) {
			Robot.driveSubsystem.acradeDrive(0.0, +0.5); // turn right
		} else if (timer.get() >= 3 && timer.get() < 5) {
			Robot.driveSubsystem.acradeDrive(-1.0, 0.0); // 2nd forward
		} else {
			Robot.grabbersbusytem.releaseBlock();
			isFinished = true;
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return isFinished;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.grabbersbusytem.stop();
		Robot.driveSubsystem.stopMotor();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
