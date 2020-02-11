/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.shooter_command;

/**
 * Add your docs here.
 */
public class shooter_subsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  WPI_TalonSRX topMotor = null;
  WPI_TalonSRX bottomMotor = null;

public shooter_subsystem(){
  topMotor = new WPI_TalonSRX(RobotMap.MOTOR_TOP);
  bottomMotor = new WPI_TalonSRX(RobotMap.MOTOR_BOTTOM);
}
public void motorSpeed(double topSpeed, double botSpeed){
  topMotor.set(topSpeed);
  bottomMotor.set(botSpeed);
  System.out.println("speeds" + topSpeed + " " + botSpeed);
}

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new shooter_command());
  }
}
