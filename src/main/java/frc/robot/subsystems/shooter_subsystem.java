/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.jni.CANSparkMaxJNI;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.shooter_command;
/**
 * Add your docs here.
 */
public class shooter_subsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands. 
  CANSparkMax bottomMotor;
  CANSparkMax topMotor;
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new shooter_command());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
  public shooter_subsystem(){
    topMotor = new CANSparkMax(RobotMap.MOTOR_SHOOT_TOP, MotorType.kBrushless);
    bottomMotor = new CANSparkMax(RobotMap.MOTOT_SHOOT_BOTTOM, MotorType.kBrushless);
  }
  public void setShooterSpeeds(double speedTop, double speedBottom){
    topMotor.set(speedTop);
    bottomMotor.set(speedBottom);
  }
}
