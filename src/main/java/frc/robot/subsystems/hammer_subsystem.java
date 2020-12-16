/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

//import edu.wpi.first.wpilibj2.command.CommandScheduler;//https://docs.wpilib.org/en/stable/docs/software/commandbased/subsystems.html#setting-default-commands
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.hammer_command;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.RobotMap;

public class hammer_subsystem extends SubsystemBase {
  WPI_TalonSRX hamMotor = null;
  /**
   * Creates a new hammer_subsystem.
   */
  public hammer_subsystem() {
    setDefaultCommand(new hammer_command());
    hamMotor = new WPI_TalonSRX(RobotMap.BUTTON_A);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void hammerActivate() {
    hamMotor.set(0.3);
  }
}
