/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
//import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.lift_command;

/**
 * Add your docs here.
 */
public class lift_subsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  WPI_TalonSRX liftMotor = null;

  public lift_subsystem(){
    liftMotor = new WPI_TalonSRX(RobotMap.LIFTMOTOR);
  }
  public void motorSpeed(double speed){
    liftMotor.set(speed);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new lift_command());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
