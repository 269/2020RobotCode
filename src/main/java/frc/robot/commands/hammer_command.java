/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class hammer_command extends Command {
  private boolean astat;
  public hammer_command() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.hammer_subsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    astat = Robot.m_oi.buttonA.get();
    SmartDashboard.putBoolean("Hammer Button", astat);
    //System.out.println("A stat:" + astat);
    if (Robot.m_oi.buttonA.get()) {
      Robot.hammer_subsystem.hammerActivate(0.3);// other idea would be to make it take a boolean that instructs it whether to be active
    } else {
      Robot.hammer_subsystem.hammerActivate(0);// other idea would be to make it take a boolean that instructs it whether to be active
      //Robot.hammer_subsystem.hammerActivate(false);
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
    Robot.hammer_subsystem.hammerActivate(0.3);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
