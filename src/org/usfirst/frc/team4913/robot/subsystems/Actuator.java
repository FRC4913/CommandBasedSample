package org.usfirst.frc.team4913.robot.subsystems;

import org.usfirst.frc.team4913.robot.RobotMap;
import org.usfirst.frc.team4913.robot.commands.ActuatorMove;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Actuator extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	// Left Trigger from 0 to 1
	// Right Trigger from 0 to 1

	Spark actuatorMotor = new Spark(RobotMap.ACTUATOR_MOTOR_PORT);

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new ActuatorMove());

	}

	public void move(double speed) {
		actuatorMotor.set(speed);
	}

	public void stop() {
		actuatorMotor.set(0);
	}
}
