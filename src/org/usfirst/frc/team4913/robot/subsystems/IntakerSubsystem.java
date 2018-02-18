package org.usfirst.frc.team4913.robot.subsystems;
import org.usfirst.frc.team4913.robot.OI;
import org.usfirst.frc.team4913.robot.RobotMap;
import org.usfirst.frc.team4913.robot.commands.GrabberControls;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakerSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.	
	WPI_TalonSRX grabberMotor = new WPI_TalonSRX(RobotMap.INTAKER_MOTOR_PORT);
	public static double PUSH_SPEEDCONSTANT = 1;
	public static double PULL_SPEEDCONSTANT = 0.5;
	public static double STOP_SPEEDCONSTANT = 0;
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new GrabberControls());
    }
    
    public void releaseBlock() {
        	grabberMotor.set(PUSH_SPEEDCONSTANT);
    }
    
    public void grabBlock() {
    		grabberMotor.set(PULL_SPEEDCONSTANT);
    }
    
    public void stop() {
    	grabberMotor.set(STOP_SPEEDCONSTANT);
    }
}

