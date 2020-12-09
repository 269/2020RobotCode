/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class driveOverLine extends CommandGroup {
  /**Robot drives 100 inches (enough to at least go over the line).
   * 
   */
  public driveOverLine() {
    addSequential(new driveStraight_command(0.8, 100, -1));
  }
}
