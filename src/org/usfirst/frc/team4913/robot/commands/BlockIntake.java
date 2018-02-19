package org.usfirst.frc.team4913.robot.commands;

import static org.usfirst.frc.team4913.robot.Robot.intaker;

import edu.wpi.first.wpilibj.command.Command;


/**
 *
 */
public class BlockIntake extends Command {

	public BlockIntake() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		super("BlockIntake");
		requires(intaker);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		intaker.intakeBlock();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		intaker.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
