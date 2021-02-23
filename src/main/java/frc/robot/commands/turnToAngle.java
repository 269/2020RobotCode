/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

//Turning to either 0, 90, 180, or 270
public class turnToAngle extends Command {

    public double goalYaw; // the goal angle
    public double turnYaw1; // the Yaw needed to get to goal compare 1
    public double turnYaw2;
    public double startingYaw; // the angle the robot is at
    public double speed; // speed for turning
    public double currentYaw;
    public boolean turnCW;// it's true when turning clockwise
    public final double MINSPEED = 0.2;// min speed can turn
    public final double SLOWYAW = 60;// angle we want to start slowing down
    public double initialSpeed;// starting speed

    public turnToAngle() {
        requires(Robot.drive_subsystem);
    }

    /**
     * 
     * @param speed   the speed of motors
     * @param goalYaw 0,90,180, or 270
     */
    public turnToAngle(double speed, double goalYaw) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        this.speed = speed;
        this.goalYaw = goalYaw;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        startingYaw = Robot.navx.getFusedHeading();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        /*
         * //if crossing 0 if((startingYaw >= 270 && startingYaw < 0) && (goalYaw ==
         * 90)){ turnYaw1 = (360 - startingYaw) + goalYaw; } else{ turnYaw1 = goalYaw -
         * startingYaw; } //if crossing 0 if((startingYaw <= 90 && startingYaw > 0) &&
         * (goalYaw == 270)){ turnYaw2 = (360 - goalYaw) + startingYaw; } else{ turnYaw2
         * = startingYaw - goalYaw; } // equal to 0 if(goalYaw == 0){ if(startingYaw > 0
         * && startingYaw < 180){ turnYaw1 = startingYaw; }else{ turnYaw2 = 360 -
         * startingYaw; } } // equal to 180 if(goalYaw == 180){ if(startingYaw > 0 &&
         * startingYaw < 180){ turnYaw1 = startingYaw; }else{ turnYaw2 = startingYaw -
         * 180; } }
         */
        currentYaw = Robot.navx.getFusedHeading();

        turnYaw1 = Math.abs(360 - goalYaw + startingYaw);
        turnYaw2 = Math.abs(goalYaw - startingYaw);

        if (turnYaw1 < turnYaw2) {
            turnCW = true;
        } else if (turnYaw2 < turnYaw2) {
            turnCW = false;
        } else if (turnYaw1 == turnYaw2) {
            turnCW = true;
        }

        if (turnCW) { // if the robot is turning clockwise
            Robot.drive_subsystem.drive(0, 0, speed);
        } else if (turnCW == false) { // if the robot is turning counterclockwise
            Robot.drive_subsystem.drive(0, 0, -speed);
        } else if ((goalYaw - 2 <= currentYaw) && (goalYaw + 2 >= currentYaw)) {
            end();
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.drive_subsystem.drive(0, 0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}
