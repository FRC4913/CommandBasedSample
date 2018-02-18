package org.usfirst.frc.team4913.robot.subsystems;

import org.usfirst.frc.team4913.robot.RobotMap;
import org.usfirst.frc.team4913.robot.commands.GrabberControls;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Spark;

/**
 *
 */
public class RotatorSubsystem extends Subsystem {
	///////// Rotator requires a motorController with an encoder

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public static double ROTATE_INWARD_SPEED = 0.5;
	public static double ROTATE_OUTWARD_SPEED = 1;
	
	WPI_TalonSRX rotatorMotor = new WPI_TalonSRX(RobotMap.ROTATOR_MOTOR_PORT);
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new GrabberControls());
    }
    
    public void rotateInward() {
    	rotatorMotor.set(ROTATE_INWARD_SPEED);
    }
    
    public void rotateOutward() {
    	rotatorMotor.set(ROTATE_OUTWARD_SPEED);
    }
    
    public void stopRotating() {
    	rotatorMotor.set(0);
    }
}