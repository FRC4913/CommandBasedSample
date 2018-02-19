/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4913.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	// Drive motor ports
	public static int RIGHT_REAR_MOTOR_PORT = 0;
	public static int LEFT_REAR_MOTOR_PORT = 1;
	public static int RIGHT_FRONT_MOTOR_PORT = 2;
	public static int LEFT_FRONT_MOTOR_PORT = 3;

	// Rotator/Intaker (aka Grabber) ports
	public static int ROTATOR_MOTOR_PORT = 5; // Rotator need to use encoder
	public static int INTAKER_MOTOR_PORT = 6; // Not sure which MotorController to use

	// Motor controllers connected to the PWM ports
	public static int ACTUATOR_MOTOR_PORT = 0; // Spark MotorController
	public static int ELEVATOR_MOTOR_PORT = 1; // SPARK MotorController

	public static int CLIMBER_NUM1_MOTOR_PORT = 4; // TODO confirm port
	public static int CLIMBER_NUM2_MOTOR_PORT = 7;
	
	public static int VISION_INPUT_7 = 7;
	public static int VISION_INPUT_8 = 8;
	public static int VISION_INPUT_9 = 9;
	
	
	public static int XBOX_CONTROLLER_PORT = 0;
	public static int JOYSTICK_PORT = 1;
}
