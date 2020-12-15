/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;

public class autonomousSelector extends CommandGroup {
  /**
   * Add your docs here.
   */
  public autonomousSelector(double position, String priority) {
      Robot.WriteOut("Autonomous Selector Initialized");
      if(position == 1){
        Robot.WriteOut("Selected Postition 1");
        if(priority.equalsIgnoreCase("mane1")){
          Robot.WriteOut("Selected Maneuver 1 on Position 1");
          addSequential(new driveOverLine());
        }
        else if(priority.equalsIgnoreCase("mane2")){
          Robot.WriteOut("Selected Maneuver 2 on Position 1");
          addSequential(new turnRightGoStraight());
        }
      }
      else if(position == 2){
        Robot.WriteOut("Selected Postition 2");
        if(priority.equalsIgnoreCase("mane1")){
          Robot.WriteOut("Selected Maneuver 1 on Position 2");

        }
        else if(priority.equalsIgnoreCase("mane2")){
          Robot.WriteOut("Selected Maneuver 2 on Position 2");
        }
      }
  }
}
