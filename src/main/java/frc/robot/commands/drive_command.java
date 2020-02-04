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

public class drive_command extends Command {
  int gear = 0;
  final int MAXGEAR = 3;
  final int MINGEAR = 0;
  boolean toggleR = false;
  boolean toggleL = false;
  boolean releaseR = false;
  boolean releaseL = false;

  public drive_command() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.drive_subsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double leftSpeed = Robot.m_oi.driverController.getRawAxis(RobotMap.LEFT_JOYSTICK_Y);
    double rightSpeed = Robot.m_oi.driverController.getRawAxis(RobotMap.RIGHT_JOYSTICK_Y);
    //double tolerance = 0.1;
    //speed reduction
    if (Robot.m_oi.RB.get() == true) {
      releaseR = false;
    } else{
      if(releaseR == false) {
        toggleR = !toggleR;
        if (gear < MAXGEAR) {
          gear++;
        }
        releaseR = true;
      }
    }
    if (Robot.m_oi.LB.get() == true) {
      releaseL = false;
    } else{
      if(releaseL == false) {
        toggleL = !toggleL;
        if (gear > MINGEAR) {
          gear--;
        }
        releaseL = true;
      }
    }


    if (gear == 1){
      leftSpeed*=0.75;
      rightSpeed*=0.75;
    }
    if(gear == 2){
      leftSpeed*=0.5;
      rightSpeed*=0.5;
    }
    if(gear == 3){
      leftSpeed*=0.25;
      rightSpeed*=0.25;
    }
    
    System.out.println("Gear #: " + gear);


    
    Robot.drive_subsystem.drive(leftSpeed, rightSpeed);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.drive_subsystem.drive(0,0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
