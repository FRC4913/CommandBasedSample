package org.usfirst.frc.team4913.robot.commands;

import org.usfirst.frc.team4913.robot.Robot;
//import org.usfirst.frc.team4913.robot.TimedAutonomousTurnLeftDeliverCube;
//import org.usfirst.frc.team4913.robot.TimedAutonomousTurnLeftDeliverCube;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

import static org.usfirst.frc.team4913.robot.Robot.driveSubsystem;
import static org.usfirst.frc.team4913.robot.Robot.grabbersbusytem;

@Deprecated
public class TimedAutonomousTurnRightDeliverCube extends Command {

	Timer timer = new Timer();
	private boolean isFinished = false;

	public TimedAutonomousTurnRightDeliverCube() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(driveSubsystem);
		requires(grabbersbusytem);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		timer.reset();
		timer.start();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (timer.get() < 1) {
			driveSubsystem.arcadeDrive(-1.0, 0.0); // forward
		} else if (timer.get() >= 1 && timer.get() < 2) {
			driveSubsystem.arcadeDrive(0.0, -1.0); // turn right
		}  else if (timer.get() >= 2 && timer.get() < 4) {
			driveSubsystem.arcadeDrive(-1.0, 0.0); // 2nd forward
		}else if (timer.get() >= 4 && timer.get() < 5) {
			driveSubsystem.arcadeDrive(0.0, 1.0); // turn left
		} else if (timer.get() >= 5 && timer.get() < 6) {
			driveSubsystem.arcadeDrive(-1.0, 0.0); // 3nd forward
		} else if (timer.get() >= 6 && timer.get() < 7)
			grabbersbusytem.releaseBlock();
		else
			isFinished = true;
		
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return isFinished;
	}

	// Called once after isFinished returns true
	protected void end() {
		grabbersbusytem.stop();
		driveSubsystem.stopMotor();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
