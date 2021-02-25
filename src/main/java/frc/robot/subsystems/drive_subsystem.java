/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.Encoder;
//import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.Subsystem;
//import edu.wpi.first.wpilibj.SpeedControllerGroup;
//import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.drive_command;

/**
 * Controls the Drive Motors
 */
public class drive_subsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  WPI_TalonFX frontRightMotor = null;
  WPI_TalonFX frontLeftMotor = null;
  WPI_TalonFX backLeftMotor = null;
  WPI_TalonFX backRightMotor = null;
  double kSensorUnitsPerRotation = 2048;
  double kMaxRPM = 6380;
  double kGearRatio = 0.1842105263;
  double peakSensorVelocity = (kMaxRPM  / 600) * (kSensorUnitsPerRotation / kGearRatio);
  /** electic brake during neutral */
  final NeutralMode kBrakeDurNeutral = NeutralMode.Coast;
  //DifferentialDrive difDrive = null;
  MecanumDrive mecDrive = null;
  public static Encoder leftEncoder;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new drive_command());
  }

  public drive_subsystem() {
    /* newer config API */
    TalonFXConfiguration configs = new TalonFXConfiguration();
    /* select integ-sensor for PID0 (it doesn't matter if PID is actually used) */
    configs.primaryPID.selectedFeedbackSensor = FeedbackDevice.IntegratedSensor;

    frontRightMotor = new WPI_TalonFX(RobotMap.MOTOR_LEFT_1);//new WPI_TalonSRX(RobotMap.MOTOR_RIGHT_1);
    frontRightMotor.configAllSettings(configs);
    /* brake or coast during neutral */
    frontRightMotor.setNeutralMode(kBrakeDurNeutral);
    
    frontLeftMotor = new WPI_TalonFX(RobotMap.MOTOR_LEFT_2);//new WPI_TalonSRX(RobotMap.MOTOR_LEFT_1);
    frontLeftMotor.configAllSettings(configs);
    frontLeftMotor.setNeutralMode(kBrakeDurNeutral);
    
    backRightMotor = new WPI_TalonFX(RobotMap.MOTOR_RIGHT_1);//new WPI_TalonSRX(RobotMap.MOTOR_RIGHT_2);
    backRightMotor.configAllSettings(configs);
    backRightMotor.setNeutralMode(kBrakeDurNeutral);
    
    backLeftMotor = new WPI_TalonFX(RobotMap.MOTOR_RIGHT_2);//new WPI_TalonSRX(RobotMap.MOTOR_LEFT_2);
    backLeftMotor.configAllSettings(configs);
    backLeftMotor.setNeutralMode(kBrakeDurNeutral);

    //SpeedControllerGroup leftMotors = new SpeedControllerGroup(frontLeftMotor, backLeftMotor);
    //SpeedControllerGroup rightMotors = new SpeedControllerGroup(frontRightMotor, backRightMotor);

    //rightMotors.setInverted(true);
    //leftMotors.setInverted(true);

    mecDrive = new MecanumDrive(frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor);
    //difDrive = new DifferentialDrive(leftMotors, rightMotors);
    //System.out.println(difDrive.isSafetyEnabled());
  }
  /** Sets the speed of the main drive
   * @param leftSpeed speed of combined left motors from -1.0 to 1.0
   * @param rightSpeed speed of combined right motors from -1.0 to 1.0
   */
  public void drive(double y, double x, double z) {
    mecDrive.driveCartesian(-y, -x, -z);
    SmartDashboard.putNumber("X Speed", x);
    SmartDashboard.putNumber("Y Speed", y);
    SmartDashboard.putNumber("Z Speed", z);
    
    double frP = frontRightMotor.getSelectedSensorPosition(0); /* position units */
    double frV = frontRightMotor.getSelectedSensorVelocity(0); /* position units per 100ms */
    double frP_converted = frP / kSensorUnitsPerRotation;
    double frV_rpm = (frV / kSensorUnitsPerRotation * 10) * 60; /* scale per100ms to perSecond */
    SmartDashboard.putNumber("FRP", frP_converted);
    SmartDashboard.putNumber("FRV", frV_rpm);
    
    double flP = frontLeftMotor.getSelectedSensorPosition(0); /* position units */
    double flV = frontLeftMotor.getSelectedSensorVelocity(0); /* position units per 100ms */
    SmartDashboard.putNumber("FLE", frontLeftMotor.getSelectedSensorVelocity());
    
    double brP = backRightMotor.getSelectedSensorPosition(0); /* position units */
    double brV = backRightMotor.getSelectedSensorVelocity(0); /* position units per 100ms */
    SmartDashboard.putNumber("BRE", backRightMotor.getSelectedSensorVelocity());
    
    double blP = backRightMotor.getSelectedSensorPosition(0); /* position units */
    double blV = backRightMotor.getSelectedSensorVelocity(0); /* position units per 100ms */
    SmartDashboard.putNumber("BLE", backLeftMotor.getSelectedSensorVelocity());
  }
  /*public void drive(double leftSpeed, double rightSpeed){
    difDrive.tankDrive(leftSpeed, rightSpeed);
  }*/
  /*public void drive(double stopSpeed){
    difDrive.tankDrive(stopSpeed, stopSpeed);
  }*/

  public boolean isDriving() {
    if (frontRightMotor.get() != 0 || frontLeftMotor.get() != 0 || backLeftMotor.get() != 0 || backRightMotor.get() != 0) {
      return true;
    }
    return false;
  }
}
