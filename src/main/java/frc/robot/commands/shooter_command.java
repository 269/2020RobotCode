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
  protected void execute() {
    SmartDashboard.putNumber("Bottom RPM: ", Robot.shooter_subsystem.bottomEncoder.getVelocity());
    SmartDashboard.putNumber("Top RPM: ", Robot.shooter_subsystem.topEncoder.getVelocity());
    if(Robot.m_oi.Intake_RB.get()){
      Robot.shooter_subsystem.shooterSet(0, 0.4);
    }
    else if(Robot.m_oi.Intake_LB.get()){
      Robot.shooter_subsystem.shooterSet(0.4, 0);
    }
    else if(Robot.m_oi.Intake_LB.get() || Robot.m_oi.Intake_RB.get()){
      Robot.shooter_subsystem.shooterSet(0.4, 0.4);
    }
    else if(Robot.m_oi.Intake_buttonA.get()){
      Robot.shooter_subsystem.shooterSet(0.65, 0.65);
    }
    //Partly keeping intake in check (check intake_command with same buttons)
    else if(Robot.m_oi.Intake_buttonY.get()){
      Robot.shooter_subsystem.shooterSet(0.075, 0.1);
    }
    else if(Robot.m_oi.Intake_buttonB.get()){
      Robot.shooter_subsystem.shooterSet(-0.075, -0.1);
    }
    else{
      Robot.shooter_subsystem.shooterSet(0, 0);
    }
  }
  @Override
   protected boolean isFinished() {
      return false;
  }

  @Override
  protected void end() {
    Robot.shooter_subsystem.shooterSet(0, 0); // add back 0
  }
    // Make this return true when this Command no longer needs to run execute()
  

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
<<<<<<< HEAD
   end();
=======
    end();
>>>>>>> ebd7965a5aa467985b9c695cf2139d17adc028d9
  }
}
