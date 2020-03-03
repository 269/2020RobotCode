/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.lift_subsystem;

public class lift_command extends Command {
  public boolean dpad_up;
  public lift_command() {
    requires(Robot.lift_subsystem);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    dpad_up = Robot.m_oi.Driver_upButton.get();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (dpad_up = true){
      Robot.lift_subsystem.motorSpeed(0.75);
    }
    else{
      Robot.lift_subsystem.motorSpeed(0);
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
    Robot.lift_subsystem.motorSpeed(0);

  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
