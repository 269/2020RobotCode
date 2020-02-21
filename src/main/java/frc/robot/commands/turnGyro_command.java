/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.Timer;

public class turnGyro_command extends Command {
  double currentYaw;
  double targetYaw;
  double speed;
  double leftSpeed;
  double rightSpeed;
  double tol;
  double speedTol;
  double rightDist;
  double leftDist;
  public turnGyro_command(double targetYaw, double speed) {
    requires(Robot.drive_subsystem);
    this.targetYaw = targetYaw;
    this.speed = speed;
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }
  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  public static double getFullYaw(){
    if(Robot.navx.getYaw() <= 0){
      return -Robot.navx.getYaw();
    }
    else{
      return 360 - Robot.navx.getYaw();
    }
  }
  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    currentYaw = getFullYaw()+targetYaw;
    System.out.println("Yaw: "+ currentYaw);
    System.out.println("Encoder Avg: " + (Math.abs(Robot.drive_subsystem.rightEncoder.getRaw()) + Math.abs(Robot.drive_subsystem.leftEncoder.getRaw()))/2);
    rightDist = Math.abs(Robot.drive_subsystem.rightEncoder.getDistance());
    leftDist = Math.abs(Robot.drive_subsystem.rightEncoder.getDistance());
    //double leftSpeedEdit = (-((currentYaw/180)/(speedTol))+(speed+(2/speedTol)));;
    //double rightSpeedEdit = (((currentYaw/180)/(speedTol))+speed);
        if(360-tol > currentYaw && currentYaw > 180){ //If the degrees off of straight (a.k.a 0 degrees) is greater than 180 but less than 355
          leftSpeed = -speed; //Ex. 315/180 = 1.75 -> 1.75/5 = 0.35 -> -0.35 -> -0.35+(.5+(2/5)) -> -0.35 + 0.9 -> Speed = 0.55 at 315 deg
          rightSpeed = speed;
        }
        else if(180 >= currentYaw && currentYaw > tol){ //If the degrees off of straight (a.k.a 0 degrees) is less than or equal to 180 but greater than 5
          leftSpeed = speed;
          rightSpeed = -speed; //Ex. 45/180 = 0.25 -> 0.25/5 = 0.05 -> 0.05 + 0.5 -> Speed = 0.55 at 45 deg
        }
        else{ //If the degrees off of straight (a.k.a 0 degrees) is 5 greater/less than straight
          leftSpeed = 0;
          rightSpeed = 0;
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
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
