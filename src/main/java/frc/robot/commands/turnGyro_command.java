/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class turnGyro_command extends Command {
  double currentYaw;        //the current fused heading yaw for each iteration (0.0 to 360.0)
  double turnAmount;         //the amount of degrees to turn
  double startingYaw;       //records the yaw when the command is initalized
  double correctedCurYaw;   //the adjusted yaw that zeros out the navx without reseting it
  double speed;             //desired robot speed
  double leftSpeed;         //calculated left speed
  double rightSpeed;        //calulcated right speed
  //double xSpeed;
  //double ySpeed;// USELESS SPEED SUFF FOR MECANUM, ABANDONING THIS FILE
  //double zSpeed;
  double tol = 5;               //The amount of degrees in each direction the robot is considered to be within the target angle. (tolernce)
  double rightDist;         //right drive encoder value in inches travled since start of command
  double leftDist;          //left drive encoder value in inches travled since start of command
  double avgDist;           //the average of rightDist and leftDist

  int rotations;
  double remainingyaw;

  /**turns the robot a specified amount of degreees left or right
   * @param turnAmount the amount of degrees the robot will move in postive or negative direction(- turns CCW, + turns CW)
   * @param speed the speed the robot will turn (-1.0 to 1.0)
   */
  public turnGyro_command(double turnAmount, double speed) {
    requires(Robot.drive_subsystem);
    this.turnAmount = turnAmount;
    this.speed = speed;
  }
  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    startingYaw  = Robot.getFullYaw();
    rotations = (int) (startingYaw + Math.abs(turnAmount))/360; //how many times will we pass 360, yes we can rotate multiple times.
    remainingyaw = Math.abs(turnAmount)%360; //the remaining amount of degrees to rotate after all full rotations.
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    //get the corrected current yaw. (zeroed out from initial start yaw)
    currentYaw = Robot.navx.getFusedHeading();
    if (startingYaw - currentYaw < 0) {                   // current yaw - starting yaw should be 0 + any diffrence the next iteration. 
      correctedCurYaw = 360 + (startingYaw - currentYaw); //if the corrected yaw passes 0 then we need to correct it to be > 360 instead of negative.
    } else {
      correctedCurYaw = startingYaw - currentYaw;
    }
 
    if (turnAmount > 0) { //if we give it a positive number turn to the right(CW), else turn to the left.
      if (correctedCurYaw < remainingyaw || rotations > 0) {  //if we have not completed all rotations or have not reached the remaining degrees keep turning.
        if (rotations <= 1) {
          leftSpeed = (speed - ((360 - correctedCurYaw)/(180/speed)));
          rightSpeed = -(speed - ((360 - correctedCurYaw)/(180/speed)));
        } else {
          leftSpeed = speed;
          rightSpeed = -speed;
        }
        if (correctedCurYaw >= 360-tol) { //for each rotation we complete
          rotations = rotations - 1;
        }
      } else {//completed turn.
        end();
      }
    } else {  //turn to the left(CCW)
      if (correctedCurYaw > remainingyaw || rotations > 0) {  //if we have not completed all rotations or have not reached the remaining degrees keep turning.
        if (rotations <= 1) {
          leftSpeed = -(speed - ((360 - correctedCurYaw)/(180/speed)));
          rightSpeed = (speed - ((360 - correctedCurYaw)/(180/speed)));
        } else {
          leftSpeed = -speed;
          rightSpeed = speed;
        }
        if (correctedCurYaw <= tol) { //for each rotation we complete
          rotations = rotations - 1;
        }
      } else {//completed turn.
        end();
      }
    }

    // EXECUTE DRIVE INSTRUCTIONS HERE
    //Robot.drive_subsystem.drive(leftSpeed, rightSpeed);
    Robot.drive_subsystem.drive(0, 0, 0);
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
