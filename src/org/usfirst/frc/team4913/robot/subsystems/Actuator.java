package org.usfirst.frc.team4913.robot.subsystems;

import org.usfirst.frc.team4913.robot.RobotMap;
import org.usfirst.frc.team4913.robot.commands.ActuatorMove;
import org.usfirst.frc.team4913.robot.commands.GrabberControls;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.IllegalUseOfCommandException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
		/*try {
			setDefaultCommand(new GrabberControls());
		} catch (IllegalUseOfCommandException actuatorException) {
			SmartDashboard.putString("actuator_Exception", actuatorException.getMessage());
		}*/
	}

	public void move(double speed) {
		actuatorMotor.set(speed);
	}

	public void stop() {
		actuatorMotor.set(0);
	}
}
