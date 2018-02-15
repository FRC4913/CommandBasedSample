package org.usfirst.frc.team4913.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team4913.robot.RobotMap;
import org.usfirst.frc.team4913.robot.commands.GrabberControls;

import edu.wpi.first.wpilibj.VictorSP;
/**
 *
 */
public class ActuatorSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	//Left Trigger - 0 to +1
	//Right Trigger - 0 to -1
	
	VictorSP actuatorMotor = new VictorSP(RobotMap.ACTUATOR_MOTOR_PORT);
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new GrabberControls());
    }
    
    public void actuatorPush(double speed) {
    		actuatorMotor.set(speed);
    }
    
    public void actuatorPull(double speed) {
    		actuatorMotor.set(speed);
    	
    }
    
    public void actuatorStop() {
    		actuatorMotor.set(0);
    }
}

