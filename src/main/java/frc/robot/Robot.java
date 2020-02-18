/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.subsystems.colorWheel_subsystem;
import frc.robot.subsystems.drive_subsystem;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static OI m_oi;
  public static AHRS navx;
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  private static DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	private static Date date = new Date();

  //declare subsystems
  public static drive_subsystem drive_subsystem = null;
  public static colorWheel_subsystem colorWheel_subsystem = null;

  public Robot(){
    navx = new AHRS(SPI.Port.kMXP);
  }
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    drive_subsystem = new drive_subsystem();
    colorWheel_subsystem = new colorWheel_subsystem();
    m_oi = new OI();
  //  m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
  //  m_chooser.addOption("My Auto", kCustomAuto);
   // SmartDashboard.putData("Auto choices", m_chooser);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * Output at default debug level
   * @param message The plain text that you want to output directly
   */
  public static void WriteOut(String message){
      PrintMessage(message, 5);
  }
/** Prints a message to console
 * 0-5 (0=log nothing, 1=CRITICAL, 2=Errors, 3=Warnings, 4=Info, 5=everything)
 */
  public static void WriteOut(String message, int lvl){
      PrintMessage(message, lvl);
  }

  /**
   * The part that actually prints out the formated message.
   * Allows for additional global modification here.
   * @param message
   */
  private static void PrintMessage(String message, int lvl){
      if (RobotMap.DEBUG) {
          if(lvl <= RobotMap.DEBUGLVL && lvl != 0) {
              System.out.println("["+ dateFormat.format(date) + "] " + message);
          }
      }
  }

  /**
   * Returns the navx fused heading from 0.0 to 360.0
   * Combines the magnomiter, gyro, and accelerometer 
   * "gives you the direction the robots facing"
   */
  public static double getFullYaw() {
     double currentYaw = Robot.navx.getFusedHeading();
    if (Robot.navx.getYaw() <= 0) {
      currentYaw = -Robot.navx.getYaw();
    } else {
      currentYaw = 360 - Robot.navx.getYaw();
    }
    //System.out.println("yaw: "+ currentYaw);
    WriteOut("Fused Yaw: "+ currentYaw, 0);
    return currentYaw;
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    //System.out.println("Auto selected: " + m_autoSelected);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
   /* switch (m_autoSelected) {
      //case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
    */
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    if(RobotMap.DEBUG) {
      getFullYaw(); //only used for debuging.
    }
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
    
  }
}
