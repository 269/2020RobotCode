/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;
import frc.robot.RobotMap;
import frc.robot.commands.colorWheel_command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

/**
 *  color wheel stuff
 */
public class colorWheel_subsystem extends Subsystem {

  WPI_TalonSRX colorWheelWheel = null;
  private static boolean wheelInitialized = false;

  /**
   * A Rev Color Sensor V3 object is constructed with an I2C port as a 
   * parameter. The device will be automatically initialized with default 
   * parameters.
   */
  private final ColorSensorV3 m_colorSensor = new ColorSensorV3(RobotMap.i2cPort);

  /**
   * A Rev Color Match object is used to register and detect known colors. This can 
   * be calibrated ahead of time or during operation.
   * 
   * This object uses a simple euclidian distance to estimate the closest match
   * with given confidence range.
   */
  private final ColorMatch m_colorMatcher = new ColorMatch();

  /**
   * Note: Any example colors should be calibrated as the user needs, these
   * are here as a basic example.
   * Once either ALLIANCE reaches Stage 3 CAPACITY, FMS relays a
specified color (randomly selected by FMS and one (1) of the three (3) colors not currently read
by the ALLIANCE’S TRENCH color sensor) to all OPERATOR CONSOLES simultaneously.
   */
  private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429); //real bue
  private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240); //real green
  private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114); //real red
  private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113); //real yellow

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new colorWheel_command());
  }
  // constructor
  public colorWheel_subsystem() {
    m_colorMatcher.addColorMatch(kBlueTarget);
    m_colorMatcher.addColorMatch(kGreenTarget);
    m_colorMatcher.addColorMatch(kRedTarget);
    m_colorMatcher.addColorMatch(kYellowTarget);
    m_colorMatcher.setConfidenceThreshold(0.8);

    colorWheelWheel = new WPI_TalonSRX(RobotMap.MOTOR_COLOR_WHEEL);
  }
  // method called by command to get color from sensor
  /**
   * Get the current color (as defined) that the color sensor sees
   * @return
   */
  public String getColor() {
    String result;
    /**
     * The method GetColor() returns a normalized color value from the sensor and can be
     * useful if outputting the color to an RGB LED or similar. To
     * read the raw color, use GetRawColor().
     * 
     * The color sensor works best when within a few inches from an object in
     * well lit conditions (the built in LED is a big help here!). The farther
     * an object is the more light from the surroundings will bleed into the 
     * measurements and make it difficult to accurately determine its color.
     */
    final Color detectedColor = m_colorSensor.getColor();

    /**
     * Run the color match algorithm on our detected color
     */
    // String colorString;
    final ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);

    if (match.color == kBlueTarget) {
      result = "Blue";
    } else if (match.color == kRedTarget) {
      result = "Red";
    } else if (match.color == kGreenTarget) {
      result = "Green";
    } else if (match.color == kYellowTarget) {
      result = "Yellow";
    } else {
      result = "Unknown";
    }

    /**
     * Open Smart Dashboard or Shuffleboard to see the color detected by the 
     * sensor.
     */
    // SmartDashboard.putNumber("Red", detectedColor.red);
    // SmartDashboard.putNumber("Green", detectedColor.green);
    // SmartDashboard.putNumber("Blue", detectedColor.blue);
    // SmartDashboard.putString("Confidence", Math.round(match.confidence * 100) + "%");
    SmartDashboard.putString("DetectedColor", result);

    return result;
  }
  /**
   * Setting the color wheel speed motor
   * @param speed the speed at which you want to rotate the color wheel's wheel (-1.0 to 1.0)
   */
  public void rotateColorWheel(double speed) {
    if (speed > 0.5) {
      speed = 0.5;
    }
    if (speed < -0.5) {
      speed = -0.5;
    }
    colorWheelWheel.set(speed);
  }
}
