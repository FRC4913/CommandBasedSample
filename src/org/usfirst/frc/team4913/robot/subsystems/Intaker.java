package org.usfirst.frc.team4913.robot.subsystems;

import org.usfirst.frc.team4913.robot.RobotMap;
import org.usfirst.frc.team4913.robot.commands.BlockIntake;
import org.usfirst.frc.team4913.robot.commands.BlockRelease;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intaker extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	WPI_TalonSRX grabberLeftMotor = new WPI_TalonSRX(RobotMap.INTAKER_L_MOTOR_PORT);
	WPI_TalonSRX grabberRightMotor = new WPI_TalonSRX(RobotMap.INTAKER_R_MOTOR_PORT);

	public static double PUSH_SPEEDCONSTANT = 1.0; // IN
	public static double PULL_SPEEDCONSTANT = 0.5; // OUT
	public static double STOP_SPEEDCONSTANT = 0.0;

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new BlockIntake());
	}

	public void releaseBlock() {
		 grabberLeftMotor.set(PUSH_SPEEDCONSTANT);
		 grabberRightMotor.set(-PUSH_SPEEDCONSTANT);
	}

	public void intakeBlock() {
		 grabberLeftMotor.set(PULL_SPEEDCONSTANT);
		 grabberRightMotor.set(-PULL_SPEEDCONSTANT);
	}

	public void stop() {
		 grabberLeftMotor.stopMotor();
		 grabberRightMotor.stopMotor();
	}
}
