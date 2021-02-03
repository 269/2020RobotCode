/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
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
  //DifferentialDrive difDrive = null;
  MecanumDrive mecDrive = null;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new drive_command());
  }

  public drive_subsystem() {
    frontRightMotor = new WPI_TalonFX(RobotMap.MOTOR_RIGHT_1);//new WPI_TalonSRX(RobotMap.MOTOR_RIGHT_1);
    frontRightMotor.setInverted(true);
    frontLeftMotor = new WPI_TalonFX(RobotMap.MOTOR_LEFT_1);//new WPI_TalonSRX(RobotMap.MOTOR_LEFT_1);
    backRightMotor = new WPI_TalonFX(RobotMap.MOTOR_RIGHT_2);//new WPI_TalonSRX(RobotMap.MOTOR_RIGHT_2);
    backLeftMotor = new WPI_TalonFX(RobotMap.MOTOR_LEFT_2);//new WPI_TalonSRX(RobotMap.MOTOR_LEFT_2);
    backLeftMotor.setInverted(true);

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
  /*public void drive(double leftSpeed, double rightSpeed){
    difDrive.tankDrive(leftSpeed, rightSpeed);
  }*/
  public void drive(double y, double x, double z) {
    mecDrive.driveCartesian(y, -x, -z, -Robot.navx.getFusedHeading());
    SmartDashboard.putNumber("X Speed", x);
    SmartDashboard.putNumber("Y Speed", y);
    SmartDashboard.putNumber("Z Speed", z);
  }
  /*public void drive(double stopSpeed){
    difDrive.tankDrive(stopSpeed, stopSpeed);
  }*/
}
