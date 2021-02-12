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

/**
 * Teleoperated control of robot (Default Drive Command)
 */
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

    //directly map the speed from the joystick to the robot motor controllers. 
    //double leftSpeed = Robot.m_oi.leftJoystickY(Robot.m_oi.driverController);
    //double rightSpeed = Robot.m_oi.rightJoystickY(Robot.m_oi.driverController);
    double ySpeed = Robot.m_oi.leftJoystickY(Robot.m_oi.driverController);
    double xSpeed = Robot.m_oi.leftJoystickX(Robot.m_oi.driverController);
    double zSpeed = Robot.m_oi.rightJoystickX(Robot.m_oi.driverController);

    //provide a tolernce from the joystick input. 
    //Some times if your not touching it it may read a very small value. We dont want the robot to think we are trying to drive it.
    double tolerance = 0.05;
    if (xSpeed < tolerance && xSpeed > -tolerance) {
      xSpeed = 0;
    }
    if (ySpeed < tolerance && ySpeed > -tolerance) {
      ySpeed = 0;
    }
    if (zSpeed < tolerance && zSpeed > -tolerance) {
      zSpeed = 0;
    }
    /*if(leftSpeed < tolerance && leftSpeed > -tolerance ){
      leftSpeed = 0.0;
    }
    if(rightSpeed < tolerance && rightSpeed > -tolerance){
      rightSpeed = 0.0;
    }*/

    //speed reduction
    if (Robot.m_oi.RB.get()) {
      releaseR = false;
    } else {
      if(releaseR == false) {
        toggleR = !toggleR;
        if (gear < MAXGEAR) {
          gear++;
        }
        releaseR = true;
      }
    }
    if (Robot.m_oi.LB.get()) {
      releaseL = false;
    } else {
      if(releaseL == false) {
        toggleL = !toggleL;
        if (gear > MINGEAR) {
          gear--;
        }
        releaseL = true;
      }
    }
    if (gear == 1){
      //leftSpeed*=0.75;
      //rightSpeed*=0.75;
      xSpeed *= 0.7;
      ySpeed *= 0.7;
      zSpeed *= 0.7;
    } else if(gear == 2){
      //leftSpeed*=0.6;
      //rightSpeed*=0.6;
      xSpeed *= 0.45;
      ySpeed *= 0.45;
      zSpeed *= 0.45;
    } else if(gear == 3){
      //leftSpeed*=0.4;
      //rightSpeed*=0.4;
      xSpeed *= 0.2;
      ySpeed *= 0.2;
      zSpeed *= 0.2;
    } else {
      xSpeed *= 0.2;
      ySpeed *= 0.2;
      zSpeed *= 0.2;
    }
    Robot.WriteOut("Gear #: " + gear);
    SmartDashboard.putNumber("gear", gear );


    //pass the desired speed to the drive substem and make robot go!
    //Robot.drive_subsystem.drive(-leftSpeed, -rightSpeed);
    Robot.drive_subsystem.drive(ySpeed, xSpeed, zSpeed);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    //Robot.drive_subsystem.drive(0);
    Robot.drive_subsystem.drive(0, 0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
