/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.Timer;

public class driveStraight_command extends Command {
  public double startingYaw = 0;
  public double currentYaw;
  public double tol = 5;
  public double speed;
  public double leftSpeed;
  public double rightSpeed;
  public double inches;
  public double slowestSpeed = 0.35;
  Timer stopwatch = new Timer();

   // Use requires() here to declare subsystem dependencies
   // eg. requires(chassis);
  public driveStraight_command() {
    requires(Robot.drive_subsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    currentYaw=Robot.navx.getFusedHeading();
    System.out.println("yaw: "+ currentYaw);
    if(360-tol > currentYaw && currentYaw > 180){ // if turning too much to the left turn to the right
      leftSpeed = speed; // make speed mod. to make greater
      rightSpeed = speed;
    }
      
    else if(180 > currentYaw && currentYaw > tol){ // if turning too much to the right turn to the left
      leftSpeed = speed;  
      rightSpeed = speed; //Make speed mod. to make greater
    }
    else{ // if in the goal area
      leftSpeed = speed;
      rightSpeed = speed;
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
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
