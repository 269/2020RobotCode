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
import java.util.ArrayList;
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
  private boolean yPressed = false;
  private String initialColor;
  private long starttime;
  private double colorDelay = 1000; //the amount of time the sensor needs to read that color in miliseconds
  private String lastColor;

  public colorWheel_command() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.colorWheel_subsystem);//should use the bind() method and the .whenpressed for Y button instead of making default.
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    rotateSpeed = 0;
    wheelColorsArray.add("Red");
    wheelColorsArray.add("Yellow");
    wheelColorsArray.add("Blue");
    wheelColorsArray.add("Green");
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (Robot.m_oi.Intake_buttonY.get() && yPressed == false) {//this is not needed if you call the command when the button is pressed
      yPressed = true;
      initialColor = Robot.colorWheel_subsystem.getColor();
      SmartDashboard.putString("FMScolor", initialColor);
    }


    gameData = DriverStation.getInstance().getGameSpecificMessage();
    //Robot.WriteOut("Control panel sensor target: " + gameData); // color the control panel sensor needs to see for 5 sec
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
          //Robot.WriteOut("Corrupt FMS color data");
          break;
      }
      SmartDashboard.putString("targetcolor", turnTo); 
    } else {
      // blah blah
    }
    //Robot.WriteOut("Robot sensor color target: " + turnTo); // color the *ROBOT* sensor has to turn to
  
    String colorNow = Robot.colorWheel_subsystem.getColor();
    if (turnTo != null) {             // if fms HAS provided a color
      if (colorNow == turnTo) {       // if the wheel has reached its color
        if (!lastColor.equals(colorNow)) {  //for the first iteration record the time.
          lastColor = colorNow;
          starttime = System.nanoTime();
        } else {  
          if ((System.nanoTime() - starttime)/1000000 >= colorDelay) {// check if its been on that color for specified time period.
            rotateSpeed = 0;    //we got to the color so stop
            turnTo = null;
            yPressed = false;
          }
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
    } else {                          // if fms has not provided a color
      rotateSpeed = 0;
    }
    lastColor = colorNow; //sets the last color to the current color for the next iteration.
    Robot.colorWheel_subsystem.rotateColorWheel(rotateSpeed); // set motor speed to rotate speed as defined above
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
    end();
  }
}
