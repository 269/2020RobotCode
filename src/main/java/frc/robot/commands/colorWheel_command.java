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
import frc.robot.RobotMap;
import frc.robot.subsystems.colorWheel_subsystem;

import java.util.ArrayList;
// import frc.robot.subsystems.colorWheel_subsystem;
import edu.wpi.first.wpilibj.DriverStation;

public class colorWheel_command extends Command {
  public static String gameData; //letter that fms will give us
  //public static char gameData = 'B'; //letter that fms will give us
  // boolean shouldTurn = true; //not used; should the wheel be turning
  String turnTo; //color to turn to
  public static double rotateSpeed;
  public static int indexOfColor;
  public static ArrayList<String> wheelColorsArray = new ArrayList<String>(); //half of the in order color set on the real wheel
  public static ArrayList<String> newColorsArray = new ArrayList<String>(); //half of the in order color set on the real wheel
  double stopTimer;
  boolean yPressed = false;
  String initialColor;

  public colorWheel_command() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.colorWheel_subsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    rotateSpeed = 0;
    wheelColorsArray.add("Red");
    wheelColorsArray.add("Yellow");
    wheelColorsArray.add("Blue");
    wheelColorsArray.add("Green");
    stopTimer = 5.0;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (Robot.m_oi.driverController.getRawButton(RobotMap.BUTTON_Y) && yPressed == false) {
      yPressed = true;
      initialColor = Robot.colorWheel_subsystem.getColor();
    }


    gameData = DriverStation.getInstance().getGameSpecificMessage();
    System.out.println("Control panel sensor target: " + gameData); // color the *CONTROL PANEL* sensor needs to see (for 5 sec)
    if(gameData.length() > 0 && yPressed)
    {
      switch (gameData.charAt(0))
      {
          case 'B' :
          //Blue case code
          // turnTo = "Red";
          // indexOfColor = wheelColorsArray.indexOf(turnTo);
          turnTo = "Blue";
          indexOfColor = wheelColorsArray.indexOf(turnTo);
          break;
          case 'G' :
          //Green case code
          // turnTo = "Yellow";
          // indexOfColor = wheelColorsArray.indexOf(turnTo);
          turnTo = "Green";
          indexOfColor = wheelColorsArray.indexOf(turnTo);
          break;
          case 'R' :
          //Red case code
          // turnTo = "Blue";
          // indexOfColor = wheelColorsArray.indexOf(turnTo);
          turnTo = "Red";
          indexOfColor = wheelColorsArray.indexOf(turnTo);
          break;
          case 'Y' :
          //Yellow case code
          // turnTo = "Green";
          // indexOfColor = wheelColorsArray.indexOf(turnTo);
          turnTo = "Yellow";
          indexOfColor = wheelColorsArray.indexOf(turnTo);
          break;
        default :
          //This is corrupt data
          System.out.println("Corrupt FMS color data");
          break;
      }
    } else {
      // blah blah
    }
    System.out.println("Robot sensor color target: " + turnTo); // color the *ROBOT* sensor has to turn to
  
    String colorNow = Robot.colorWheel_subsystem.getColor();
    SmartDashboard.putString("Color", colorNow); // show current sensed color on SD
    if (turnTo != null) {             // if fms has provided a color
      if (colorNow == turnTo) {       // if the wheel has reached its color
        if (stopTimer <= 0) {
          rotateSpeed = 0;
          turnTo = null;
          stopTimer = 5.0;
          yPressed = false;
        } else {
          --stopTimer;                // subtract from over color stop timer, this will happen until the color timer = 0, if it still sees the target color, then the motor can stop
        }
      } else {
        switch (initialColor) {
          case "Red":
            switch (turnTo) {
              case "Red":
                rotateSpeed = 0;
                break;
              case "Yellow":
                rotateSpeed = -0.15;
                break;
              case "Blue":
                rotateSpeed = 0.15;
                break;
                case "Green":
                rotateSpeed = 0.15;
                break;
              default:
                rotateSpeed = 0;
                break;
            }
            break;
          case "Yellow":
            switch (turnTo) {
              case "Red":
                rotateSpeed = 0.15;
                break;
              case "Yellow":
                rotateSpeed = 0;
                break;
              case "Blue":
                rotateSpeed = -0.15;
                break;
                case "Green":
                rotateSpeed = 0.15;
                break;
              default:
                rotateSpeed = 0;
                break;
            }
            break;
          case "Blue":
            switch (turnTo) {
              case "Red":
                rotateSpeed = 0.15;
                break;
              case "Yellow":
                rotateSpeed = 0.15;
                break;
              case "Blue":
                rotateSpeed = 0;
                break;
                case "Green":
                rotateSpeed = -0.15;
                break;
              default:
                rotateSpeed = 0;
                break;
            }
            break;
          case "Green":
            switch (turnTo) {
              case "Red":
                rotateSpeed = -0.15;
                break;
              case "Yellow":
                rotateSpeed = 0.15;
                break;
              case "Blue":
                rotateSpeed = 0.15;
                break;
                case "Green":
                rotateSpeed = 0;
                break;
              default:
                rotateSpeed = 0;
                break;
            }
            break;
          default:
            rotateSpeed = 0;
            break;
        }
      }
    } else if (yPressed) {            // PRESUMABLY if y is pressed but no fms data is/was received
      if (Robot.colorWheel_subsystem.numOfColorWheelRotations() >= 4) { // checking to see if the color wheel has completed 4 or more rotations
        yPressed = false;             // stopping the yPressed loop
        rotateSpeed = 0;              // cancelling motor movement
        Robot.colorWheel_subsystem.resetColorWheelEncoder();
      } else {
        rotateSpeed = 0.23;           // spinning color wheel in this loop at a higher speed because it doesn't matter (I think), will continue PRESUMABLY until the color wheel has spun 4 or more times
      }
    } else {                          // if fms has not provided a color and Y is not pressed
      rotateSpeed = 0;
    }
    System.out.println(stopTimer);    // TEMPORARY logging the timer that iterates when a different color is seen to make sure it counts down
    Robot.colorWheel_subsystem.rotateColorWheel(rotateSpeed); // set motor speed to rotate speed as defined above

    /* TESTING FOR NEXT TIME TO HOPEFULLY SEE THE NUMBER OF **PULSES??** IT TAKES TO COVER CERTAIN DISTANCE WITH ENCODER */
    // system out sc "syso"
    System.out.println(Robot.colorWheel_subsystem.numOfColorWheelRotations());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    SmartDashboard.putString("Color", "No Color");
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
