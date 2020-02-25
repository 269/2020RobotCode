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
    if(Robot.m_oi.Intake_dPadLeft.get()){
      topSpeed = 3000;
      bottomSpeed = 1500;
    }
    else if(Robot.m_oi.Intake_dPadUp.get()){
      topSpeed = 3750;
      bottomSpeed = 3000;
    }
    else if(Robot.m_oi.Intake_dPadRight.get()){
      topSpeed = 5000;
      bottomSpeed = 5000;
    }
    if(Robot.m_oi.Intake_RB.get()){
      Robot.shooter_subsystem.setShooterSpeeds(topSpeed, bottomSpeed);
    }
    else{
      Robot.shooter_subsystem.setShooterSpeeds(0, 0);
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
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
