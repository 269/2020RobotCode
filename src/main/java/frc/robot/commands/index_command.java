/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.Robot;

public class index_command extends Command {
  Value kOff;
  Value kForward;
  Value kReverse;
  public index_command() {
    requires(Robot.index_subsystem);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    kOff = DoubleSolenoid.Value.kOff;
    kForward = DoubleSolenoid.Value.kForward;
    kReverse = DoubleSolenoid.Value.kReverse;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Robot.m_oi.buttonB.get()){
      System.out.println("B");
      Robot.index_subsystem.indexActivate(kForward);
    }
    else{
      Robot.index_subsystem.indexActivate(kReverse);
    }
      // If the a button is pressed the boolean value is true which activates the pnuematics of the indexing system
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.index_subsystem.indexActivate(kOff);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
