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
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.DoubleSolenoid;


/**
 * Add your docs here.
 */
public class index_subsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  DoubleSolenoid solenoid; 
  public static Compressor airComp;

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new index_command());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
  public index_subsystem(){
    airComp = new Compressor(1);
    //airComp.clearAllPCMStickyFaults();
    solenoid = new DoubleSolenoid(1, RobotMap.SOLENOID_IN, RobotMap.SOLENOID_OUT);
  }

  /** Turns on or off the compressor
   */
  public void checkCompressor(){
    System.out.println("Switch "+ airComp.getPressureSwitchValue());
    if(!airComp.getPressureSwitchValue()){ // remember to fix this so take away the !
    //airComp = new Compressor(10);
    //airComp.clearAllPCMStickyFaults();
    //airComp.stop();
    //solenoid = new DoubleSolenoid(RobotMap.SOLENOID_IN, RobotMap.SOLENOID_OUT);
    //if(airComp.getPressureSwitchValue()){
      airComp.start();
    }
    else{
      airComp.stop();
    }
  }


  /** Extends or retracts both pneumatic pistons based on 'active'
   * @param active if true pistons are extended if false they are retracted
   */
  public void indexActivate(Value status){
    System.out.println("Status " + status);// CONFLICT, CHECK VAR status
    solenoid.set(status);
  }

}
