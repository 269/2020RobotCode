/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class shooter_command extends Command {
 // double topSpeed;
  double bottomSpeed;
  double bottomSpeedSet = 0.8;
  double topSpeedSet = 0.8;
  //double maxRPM = 5700;
  //double topSpeedSet = 0.8;
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
  /*  if(Robot.m_oi.Intake_leftButton.get()){
      topSpeed = 3000;
      bottomSpeed = 1500;
      topSpeedSet = 0.5;
      bottomSpeedSet = 0.5;
      System.out.println("Low spd");
    }
    else if(Robot.m_oi.Intake_upButton.get()){
      topSpeed = 3750;
      bottomSpeed = 3000;
      //topSpeedSet = topSpeed/maxRPM;
      bottomSpeedSet = bottomSpeed/maxRPM;
      System.out.println("Med spd");
    }
    else if(Robot.m_oi.Intake_rightButton.get()){
      topSpeed = 5000;
      bottomSpeed = 5000;
     // topSpeedSet = topSpeed/maxRPM;
      bottomSpeedSet = bottomSpeed/maxRPM;
    }*/
    SmartDashboard.putNumber("Bottom RPM", Robot.shooter_subsystem.bottomEncoder.getVelocity());
    if(Robot.m_oi.Intake_RB.get()){
      //Robot.shooter_subsystem.setShooterSpeeds(topSpeed, bottomSpeed);
      Robot.shooter_subsystem.setShooterSpeeds(0,2000);
    }
    else if(Robot.m_oi.Intake_LB.get()){
      Robot.shooter_subsystem.setShooterSpeeds(2000,0);
    }
    else if(Robot.m_oi.Intake_LB.get() || Robot.m_oi.Intake_RB.get()){
      Robot.shooter_subsystem.setShooterSpeeds(2000,2000);
    }
    else if(Robot.m_oi.Intake_buttonA.get()){
      Robot.shooter_subsystem.setShooterSpeeds(3000,3000);
    }
    else if(Robot.m_oi.Intake_buttonY.get()){
      Robot.shooter_subsystem.shootAnywayTop(0.075);
      Robot.shooter_subsystem.shootAnywayBottom(0.1);
    }
    else if(Robot.m_oi.Intake_buttonB.get()){
      Robot.shooter_subsystem.shootAnywayTop(-0.075);
      Robot.shooter_subsystem.shootAnywayBottom(-0.1);    
    }
    else{
      Robot.shooter_subsystem.shootAnywayBoth(0);
    }
  }
  @Override
   protected boolean isFinished() {
      return false;
  }

  @Override
  protected void end() {
   // Robot.shooter_subsystem.setShooterSpeeds(0, 0);
    Robot.shooter_subsystem.shootAnywayBoth(0); // add back 0
  }
    // Make this return true when this Command no longer needs to run execute()
  

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
