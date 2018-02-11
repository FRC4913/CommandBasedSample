/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4913.robot.subsystems;

import org.usfirst.frc.team4913.robot.OI;
import org.usfirst.frc.team4913.robot.RobotMap;
import org.usfirst.frc.team4913.robot.commands.Drive;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * An example subsystem. You can replace me with your own Subsystem.
 */
public class DriveSubsystem extends Subsystem {

	double ySpeed = 0;
	double yControllerInput;
	double scaledYcontrollerInput;
	double ySpeedScale = 0.01;// placeholder
	double yDiff;
	double scaledYDiff;

	WPI_TalonSRX rightRearMotor = new WPI_TalonSRX(RobotMap.RIGHT_REAR_MOTOR_PORT);
	WPI_TalonSRX leftRearMotor = new WPI_TalonSRX(RobotMap.LEFT_REAR_MOTOR_PORT);
	WPI_TalonSRX rightFrontMotor = new WPI_TalonSRX(RobotMap.RIGHT_FRONT_MOTOR_PORT);
	WPI_TalonSRX leftFrontMotor = new WPI_TalonSRX(RobotMap.LEFT_FRONT_MOTOR_PORT);

	SpeedControllerGroup leftGroup = new SpeedControllerGroup(leftFrontMotor, leftRearMotor);
	SpeedControllerGroup rightGroup = new SpeedControllerGroup(rightFrontMotor, rightRearMotor);

	DifferentialDrive robotDrive = new DifferentialDrive(leftGroup, rightGroup);

	public void initDefaultCommand() {
		setDefaultCommand(new Drive());
	}

	public void stopMotor() {
		robotDrive.stopMotor();
	}

	public void arcadeDrive() {
		yControllerInput = OI.controller.getY(Hand.kLeft);
		yDiff = yControllerInput - ySpeed;
		scaledYDiff = yDiff * ySpeedScale;
		ySpeed += scaledYDiff;
		robotDrive.arcadeDrive(ySpeed, -OI.controller.getX(Hand.kLeft));
	}

	public void acradeDrive(double ySpeed, double xSpeed) {
		robotDrive.arcadeDrive(ySpeed, xSpeed);
	}
}
