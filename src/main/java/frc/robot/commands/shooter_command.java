/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.shooter_subsystem;
import frc.robot.Robot;

public class shooter_command extends Command {
  boolean buttonPress;
  boolean prevRele = true; //previously released 
  boolean active = false;

  public shooter_command() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.shooter_subsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
   //Toggles Shooter On And Off On Button Release
   buttonPress = Robot.m_oi.RB1.get();
   if (buttonPress && prevRele){
     active = !active;
     prevRele = false;
   }
   else if(!buttonPress){
     prevRele = true;
   }
   if(active){
     Robot.shooter_subsystem.motorSpeed(0.95,1);
   }
   else{
    Robot.shooter_subsystem.motorSpeed(0,0);
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
    Robot.shooter_subsystem.motorSpeed(0,0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
