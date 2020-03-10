/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class shooter_command extends Command {
  double topSpeed;
  double bottomSpeed;
  double bottomSpeedSet = 0.8;
  double topSpeedSet = 0.8;
  double maxRPM = 5700;
  public shooter_command() {
    requires(Robot.shooter_subsystem);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {//Different speeds are set based on what position the d-pad was pushed
    if(Robot.m_oi.Intake_leftButton.get()){
      topSpeed = 3000;
      bottomSpeed = 1500;
      topSpeedSet = 0.5;
      bottomSpeedSet = 0.5;
      System.out.println("Low spd");
    }
    else if(Robot.m_oi.Intake_upButton.get()){
      topSpeed = 3750;
      bottomSpeed = 3000;
      topSpeedSet = topSpeed/maxRPM;
      bottomSpeedSet = bottomSpeed/maxRPM;
      System.out.println("Med spd");
    }
    else if(Robot.m_oi.Intake_rightButton.get()){
      topSpeed = 5000;
      bottomSpeed = 5000;
      topSpeedSet = topSpeed/maxRPM;
      bottomSpeedSet = bottomSpeed/maxRPM;
      System.out.println("High spd");
    }
    if(Robot.m_oi.Intake_RB.get()){
      //Robot.shooter_subsystem.setShooterSpeeds(topSpeed, bottomSpeed);
      Robot.shooter_subsystem.shootAnywayTop(topSpeedSet);
      Robot.shooter_subsystem.shootAnywayBottom(bottomSpeedSet);
    }
    else{
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
    Robot.shooter_subsystem.setShooterSpeeds(0, 0);
    Robot.shooter_subsystem.shootAnywayTop(0);
    Robot.shooter_subsystem.shootAnywayBottom(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
