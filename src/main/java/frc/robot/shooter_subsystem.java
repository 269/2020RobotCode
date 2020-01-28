/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * Add your docs here.
 */
public class shooter_subsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  // Motors
  WPI_TalonSRX topMotor = null;
  WPI_TalonSRX bottomMotor = null;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new shooter_command());
  }
  public shooter_subsystem(){
    topMotor = new WPI_TalonSRX(RobotMap.SMOTOR_TOP);
    bottomMotor = new WPI_TalonSRX(RobotMap.SMOTOR_BOTTOM);
  }
  public void shoot(double topSpeed, double botSpeed){
    topMotor.set(topSpeed);
    bottomMotor.set(botSpeed);
  }
}
