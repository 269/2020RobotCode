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
  public double rightDist;
  public double leftDist;
  public double distTol;
  public static driveStraight_command driveStraight = null;
  Timer stopwatch = new Timer();

   // Use requires() here to declare subsystem dependencies
   // eg. requires(chassis);

   /** Setting the robot to 'drive straight' for a certain amount of time or distance
    * @param speed the speed at which the robot should move
    * @param inches the distance at which the robot should travel
    * @param timeout the time at which the robot will stop moving
    */
  public driveStraight_command(double speed, double inches, double timeout) {
    requires(Robot.drive_subsystem);
    this.leftSpeed = speed;
    this.rightSpeed = speed;
    this.speed = speed;
    this.inches = inches;
    this.timeout = timeout;
    this.timeout = timeout;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.leftEncoder.reset();
    Robot.rightEncoder.reset();
    Robot.navx.reset();
    stopwatch.reset();
    stopwatch.start();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    currentYaw = Robot.getFullYaw();
    System.out.println("Yaw: "+ currentYaw);
    System.out.println("Encoder Avg: " + (Math.abs(Robot.rightEncoder.getRaw()) + Math.abs(Robot.leftEncoder.getRaw()))/2);
    rightDist = Math.abs(Robot.rightEncoder.getDistance());
    leftDist = Math.abs(Robot.rightEncoder.getDistance());
      double leftSpeedEdit = (-((currentYaw/180)/(speedTol))+(speed+(2/speedTol)));;
      double rightSpeedEdit = (((currentYaw/180)/(speedTol))+speed);
          if(360-tol > currentYaw && currentYaw > 180){ //If the degrees off of straight (a.k.a 0 degrees) is greater than 180 but less than 355
            leftSpeed = -leftSpeedEdit; //Ex. 315/180 = 1.75 -> 1.75/5 = 0.35 -> -0.35 -> -0.35+(.5+(2/5)) -> -0.35 + 0.9 -> Speed = 0.55 at 315 deg
            rightSpeed = -speed;
          }
          else if(180 >= currentYaw && currentYaw > tol){ //If the degrees off of straight (a.k.a 0 degrees) is less than or equal to 180 but greater than 5
            leftSpeed = -speed;
            rightSpeed = -rightSpeedEdit; //Ex. 45/180 = 0.25 -> 0.25/5 = 0.05 -> 0.05 + 0.5 -> Speed = 0.55 at 45 deg
          }
          else{ //If the degrees off of straight (a.k.a 0 degrees) is 5 greater/less than straight
            leftSpeed = -speed;
            rightSpeed = -speed;
          }

      System.out.println("Left distance: " + leftDist);
      System.out.println("Right distance: " + rightDist);
      System.out.println("Left Spd: " + leftSpeed);
      System.out.println("Right Spd: " + rightSpeed);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(stopwatch.get() == timeout){
      return true;
    }
    else{
      return false;
    }
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
