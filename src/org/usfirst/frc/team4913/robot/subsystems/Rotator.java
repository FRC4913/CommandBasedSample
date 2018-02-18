package org.usfirst.frc.team4913.robot.subsystems;

import org.usfirst.frc.team4913.robot.RobotMap;
import org.usfirst.frc.team4913.robot.commands.RotatorMove;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Rotator extends Subsystem {
	///////// Rotator requires a motorController with an encoder

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public static double ROTATE_INWARD_SPEED = 0.5;
	public static double ROTATE_OUTWARD_SPEED = 1;
	
	WPI_TalonSRX rotatorMotor = new WPI_TalonSRX(RobotMap.ROTATOR_MOTOR_PORT);
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new RotatorMove());
    }

    public void move(double speed) {
    	rotatorMotor.set(speed);
    }

    public void stop() {
    	rotatorMotor.set(0);
    }
}