package org.usfirst.frc.team4913.robot.subsystems;

import org.usfirst.frc.team4913.robot.RobotMap;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public static double LIFTER_UP_SPEED = 1;
	public static double LIFTER_DOWN_SPEED = 0.5;
	
	Spark elevatorMotor = new Spark(RobotMap.ELEVATOR_MOTOR_PORT);
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void up() {
    	elevatorMotor.set(LIFTER_UP_SPEED);
    }
    
    public void down() {
    	elevatorMotor.set(LIFTER_DOWN_SPEED);
    }
    
    public void stop() {
    	elevatorMotor.set(0);
    }
}
