/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class intake_command extends Command {
  double intakeSpeed = 0.85;
  public intake_command() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.intake_subsystem);
  }
  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Robot.m_oi.Intake_buttonX.get()){
      Robot.intake_subsystem.rollerSpeed(intakeSpeed);
    }
    else if(Robot.m_oi.Intake_buttonY.get()){
      Robot.intake_subsystem.rollerSpeed(1.0);
    }
    else if(Robot.m_oi.Intake_buttonB.get()){
      Robot.intake_subsystem.rollerSpeed(1.0);
    }
    else if(Robot.m_oi.Intake_selectButton.get()){
      Robot.intake_subsystem.rollerSpeed(-1.0);
    }
    else if(Robot.m_oi.Intake_buttonB.get()){
      Robot.intake_subsystem.rollerSpeed(-intakeSpeed);
    }
    else{
      Robot.intake_subsystem.rollerSpeed(0);
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
    Robot.intake_subsystem.rollerSpeed(0);
    //Robot.shooter_subsystem.shootAnywayBoth(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
