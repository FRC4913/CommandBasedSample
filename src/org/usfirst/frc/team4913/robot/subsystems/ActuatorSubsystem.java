package org.usfirst.frc.team4913.robot.subsystems;

import edu.wpi.first.wpilibj.command.IllegalUseOfCommandException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4913.robot.RobotMap;
import org.usfirst.frc.team4913.robot.commands.GrabberControls;

import edu.wpi.first.wpilibj.VictorSP;

/**
 *
 */
public class ActuatorSubsystem extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	// Left Trigger from 0 to 1
	// Right Trigger from 0 to 1

	VictorSP actuatorMotor = new VictorSP(RobotMap.ACTUATOR_MOTOR_PORT);

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		try {
			setDefaultCommand(new GrabberControls());
		} catch (IllegalUseOfCommandException actuatorException) {
			SmartDashboard.putString("actuator_Exception", actuatorException.getMessage());
		}
	}

	public void actuatorPush(double speed) {
		actuatorMotor.set(-speed);
	}

	public void actuatorPull(double speed) {
		actuatorMotor.set(-speed);

	}

	public void actuatorStop() {
		actuatorMotor.set(0);
	}
}
