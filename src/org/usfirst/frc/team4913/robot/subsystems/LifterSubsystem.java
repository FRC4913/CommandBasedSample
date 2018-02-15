package org.usfirst.frc.team4913.robot.subsystems;

import org.usfirst.frc.team4913.robot.RobotMap;
import org.usfirst.frc.team4913.robot.commands.GrabberControls;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LifterSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public static double LIFTER_UP_SPEED = 1;
	public static double LIFTER_DOWN_SPEED = 0.5;
	
	Spark elevatorMotor = new Spark(RobotMap.ELEVATOR_MOTOR_PORT);
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void Up() {
    	elevatorMotor.set(LIFTER_UP_SPEED);
    }
    
    public void Down() {
    	elevatorMotor.set(LIFTER_DOWN_SPEED);
    }
    
    public void Stop() {
    	elevatorMotor.set(0);
    }
}
