/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.index_command;
import edu.wpi.first.wpilibj.Compressor;


/**
 * Add your docs here.
 */
public class index_subsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  Solenoid solenoid_right; 
  Solenoid solenoid_left; 
  Compressor airComp;

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new index_command());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
  public index_subsystem(){
    airComp = new Compressor(RobotMap.AIRCOMP);
    solenoid_right = new Solenoid(RobotMap.SOLENOID_1);
    solenoid_left = new Solenoid(RobotMap.SOLENOID_2);
    if(airComp.getPressureSwitchValue()){
      airComp.start();
    }
    else{
      airComp.stop();
    }
  }
  
  public void indexActivate(boolean active){
    solenoid_right.set(active);
    solenoid_left.set(active);
  }

}
