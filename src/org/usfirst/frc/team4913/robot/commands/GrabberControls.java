package org.usfirst.frc.team4913.robot.commands;

import org.usfirst.frc.team4913.robot.OI;
import org.usfirst.frc.team4913.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
@Deprecated
public class GrabberControls extends Command {

    public GrabberControls() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    		requires(Robot.intakerSubsystem);
    		//requires(Robot.rotator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    		//Intaker --- spinning wheels at the front
    		if(OI.xboxController.getY(Hand.kLeft) > 0.1)
    			Robot.intakerSubsystem.grabBlock();  // Pull the left stick of XboxController to grabBlock using Intaker
    		else if(OI.xboxController.getY(Hand.kLeft) < -0.1)
    			Robot.intakerSubsystem.releaseBlock(); // Push the left stick of XboxController to ShootBlock using Intaker
    		else if(Math.abs(OI.xboxController.getY(Hand.kLeft)) < 0.1)
    			Robot.intakerSubsystem.stop();
    		
    		//Rotator --- Arm that adjust the angle of the Intaker
    		/*if(OI.xboxController.getY(Hand.kRight) > 0.1)
            Robot.rotator.rotateInward(); // Pull the Right stick of XboxController Rotate Up the Rotator for pick up
        	else if(OI.xboxController.getY(Hand.kRight) < -0.1)
        		Robot.rotator.rotateOutward(); // Push the Right stick of XboxController Rotate Down the Rotator for pick up
        	else if(Math.abs(OI.xboxController.getY(Hand.kRight)) < 0.1)
        		Robot.rotator.stopRotating();*/
    		
    		//Actuator --- Push up the Rotator and Intaker for shooting cubes
    		/*if(OI.xboxController.getTriggerAxis(Hand.kLeft) > 0.1) {
    			Robot.actuator.down(-OI.xboxController.getTriggerAxis(Hand.kLeft)); // Left Trigger contract the length of the Actuator
    		}else if(OI.xboxController.getTriggerAxis(Hand.kRight) > 0.1) {
    			Robot.actuator.move(OI.xboxController.getTriggerAxis(Hand.kRight)); // Right Trigger expand the length of the Actuator
    		}else if(Math.abs(OI.xboxController.getTriggerAxis(Hand.kRight)) < 0.1 && Math.abs(OI.xboxController.getTriggerAxis(Hand.kLeft)) < 0.1) {
    			Robot.actuator.stop();
    		}*/
    		
    		//Lifter is in the OI.Java declared at the buttons. --- Adjust Up or Down of the whole robot's grabberSystem.
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
