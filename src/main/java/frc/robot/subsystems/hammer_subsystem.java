/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

//import edu.wpi.first.wpilibj2.command.CommandScheduler;//https://docs.wpilib.org/en/stable/docs/software/commandbased/subsystems.html#setting-default-commands
import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.RobotMap;
import frc.robot.commands.hammer_command;

/**
 * Add your docs here.
 */
public class hammer_subsystem extends Subsystem {
  WPI_TalonSRX hamMotor = null;
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  /**
   * Creates a new hammer_subsystem.
   */
  public hammer_subsystem() {
    hamMotor = new WPI_TalonSRX(RobotMap.MOTOR_HAMMER_1);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new hammer_command());
  }

  public void hammerActivate(double spd) {
    hamMotor.set(spd);
  }
}
