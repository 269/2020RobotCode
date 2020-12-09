/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class turnRightGoStraight extends CommandGroup {
  /**Robot turns to the "right" 90 degrees and moves straight 100 inches.
   * 
   */
  public turnRightGoStraight() {
    addSequential(new turnGyro_command(90, .5));
    addSequential(new driveStraight_command(0.5, 100, -1));
  }
}
