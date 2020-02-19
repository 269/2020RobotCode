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

public class driveStraight_command extends Command {
  public double startingYaw = 0;
  public double currentYaw;
  public double tol = 1.5;
  public double speed;
  public double speedTol = 2;
  public double leftSpeed;
  public double rightSpeed;
  public double inches;
  public double timeout;
  public boolean timeoutActivated;
  public double rightDist;
  public double leftDist;
  public double distTol;
  public double targetYaw;
  public static driveStraight_command driveStraight = null;
  Timer stopwatch = new Timer();

   // Use requires() here to declare subsystem dependencies
   // eg. requires(chassis);
  public driveStraight_command(double speed, double inches, double targetYaw) {
    requires(Robot.drive_subsystem);
    this.speed = speed;
    this.inches = inches;
    this.targetYaw = targetYaw;
    timeoutActivated = false;
  }
  /*public driveStraight_command(double speed, double inches, double timeout){
    requires(Robot.drive_subsystem);
    this.leftSpeed = speed;
    this.rightSpeed = speed;
    this.speed = speed;
    this.inches = inches;
    this.timeout = timeout;
    timeoutActivated = true;
    Robot.drive_subsystem.drive(leftSpeed, rightSpeed);
  }*/
  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.drive_subsystem.leftEncoder.reset();
    Robot.drive_subsystem.rightEncoder.reset();
    targetYaw = Robot.navx.getFusedHeading();
    stopwatch.reset();
    stopwatch.start();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    currentYaw = Robot.navx.getFusedHeading();
    System.out.println("Yaw: "+ currentYaw);
    System.out.println("Encoder Avg: " + (Math.abs(Robot.drive_subsystem.rightEncoder.getRaw()) + Math.abs(Robot.drive_subsystem.leftEncoder.getRaw()))/2);
    rightDist = Math.abs(Robot.drive_subsystem.rightEncoder.getDistance());
    leftDist = Math.abs(Robot.drive_subsystem.rightEncoder.getDistance());
    //if(speed+(1/speedTol) <= 1){ //Speed cant be 1 or it will overflow (go past 1) since speedTol is 5
    //if(Robot.m_oi.buttonA.get()){
      if(targetYaw > 180){
        targetYaw = 360-targetYaw;
      }
      if(((rightDist+leftDist)/2) + 5 <= inches){ //If the encoders from both sides have an average distance plus a tolerance of distTol inches
        if(targetYaw-tol > currentYaw && currentYaw > 180+targetYaw){ //If the degrees off of straight (a.k.a 0 degrees) is greater than 180 but less than 355
          leftSpeed = -((-((currentYaw/180)/(speedTol))+((-speed)+(2/speedTol)))); //Ex. 315/180 = 1.75 -> 1.75/5 = 0.35 -> -0.35 -> -0.35+(.5+(2/5)) -> -0.35 + 0.9 -> Speed = 0.55 at 315 deg
          rightSpeed = speed;
          System.out.println("Outta left");
          System.out.println("Outta left");
          System.out.println("Outta left");
        }
        else if(180+targetYaw >= currentYaw && currentYaw > tol+targetYaw){ //If the degrees off of straight (a.k.a 0 degrees) is less than or equal to 180 but greater than 5
          leftSpeed = speed;
          rightSpeed = -((((currentYaw/180)/(speedTol))+(-speed))); //Ex. 45/180 = 0.25 -> 0.25/5 = 0.05 -> 0.05 + 0.5 -> Speed = 0.55 at 45 deg
          System.out.println("Outta right");
          System.out.println("Outta right");
          System.out.println("Outta right");
        }
        else{ //If the degrees off of straight (a.k.a 0 degrees) is 5 greater/less than straight
          leftSpeed = speed;
          rightSpeed = speed;
          System.out.println("Norm");
          System.out.println("Norm");
          System.out.println("Norm");
        }
        Robot.drive_subsystem.drive(leftSpeed, rightSpeed);
      }  
      else{
        Robot.drive_subsystem.drive(0);
      }
      //}
      
      /*else{
        System.out.println("Button not pressed");
      }*/
      //}
      /*else{
        System.out.println("Overpowered error!");
      }*/
      System.out.println("Left distance: " + leftDist);
      System.out.println("Right distance: " + rightDist);
      System.out.println("Left Spd: " + leftSpeed);
      System.out.println("Right Spd: " + rightSpeed);
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
