/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.index_command;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX; 


/**
 * Add your docs here.
 */
public class index_subsystem extends Subsystem {
  private WPI_TalonSRX spinIntake;

  /** Sets intake roller speed
   * @param motorSpeed sets the speed of the intake roller
   */
  public index_subsystem(){
    spinIntake = new WPI_TalonSRX(RobotMap.MOTOR_INTAKE);
  }
  public void rollerSpeed(double motorSpeed){
    spinIntake.set(motorSpeed);
  }

  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new index_command());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
