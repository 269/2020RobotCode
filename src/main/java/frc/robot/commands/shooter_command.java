/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.shooter_subsystem;

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
    if(Robot.m_oi.dPadRightInt.get()){
      topSpeed = 0.5;
      bottomSpeed = 0.3;
    }
    else if(Robot.m_oi.dPadUpInt.get()){
      topSpeed = 0.75;
      bottomSpeed = 0.6;
    }
    else if(Robot.m_oi.dPadLeftInt.get()){
      topSpeed = 1;
      bottomSpeed = 1;
    }
    if(Robot.m_oi.RBInt.get()){
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
