/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
//import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.shooter_command;
/**
 * Add your docs here.
 */
public class shooter_subsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  CANPIDController bottomPID;
  CANPIDController topPID;
  public CANEncoder topEncoder;
  public CANEncoder bottomEncoder;
  CANSparkMax bottomMotor;
  CANSparkMax topMotor;
  // VVV IN MERGE CONFLICT, NOT SURE WHAT IT IS, APPLIES TO FUNCTION IN MERGE CONFLICT BELOW
  // CANSparkMax topMotor;
  //double kP = 0;
  //double kI = 0;
  //double kD = 0;
  //double kIz = 0;
  //double kFF = 0;
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new shooter_command());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
  //Declaring PID and motors for shooter
  public shooter_subsystem(){
    // START PREV MERGE CONFLICT
    topMotor = new CANSparkMax(RobotMap.MOTOR_SHOOT_TOP, MotorType.kBrushless);
    bottomMotor = new CANSparkMax(RobotMap.MOTOR_SHOOT_BOTTOM, MotorType.kBrushless);
    topEncoder = new CANEncoder(topMotor);
    bottomEncoder = new CANEncoder(bottomMotor);
    topMotor.setInverted(true);
  }
  /** Sets the shooters speeds (in RPM)
  *@param topMotorSpeed the speed for the top of the shooter (-1 to 1)
  *@param bottomMotorSpeed the speed for the bottom of the shooter (-1 to 1)
  */
  public void shooterSet(double topMotorSpeed, double bottomMotorSpeed){
    topMotor.set(topMotorSpeed);
    bottomMotor.set(bottomMotorSpeed);
    
    // VVV ALL BELOW IN MERGE CONFLICT
    
    //topMotor = new CANSparkMax(RobotMap.MOTOR_SHOOT_TOP, MotorType.kBrushless);
    //bottomMotor = new CANSparkMax(RobotMap.MOTOT_SHOOT_BOTTOM, MotorType.kBrushless);
    //topPID = topMotor.getPIDController();
    //bottomPID = bottomMotor.getPIDController();
    //topPID.setReference(0, ControlType.kSmartMotion);
    //bottomPID.setReference(0, ControlType.kSmartMotion);
    //topPID.setP(kP);
    //topPID.setI(kI);
    //topPID.setFF(kFF);
    //topPID.setIZone(kIz);
    //bottomPID.setP(kP);
    //bottomPID.setI(kI);
    //bottomPID.setFF(kFF);
    //bottomPID.setIZone(kIz); */
  
  } // NOT CONFLICTING IN MERGE
  
  /** Sets the shooters speeds (in RPM)
  *@param topRPM the max RPM for the top of the shooter (between 5700RPM-1000RPM)
  *@param bottomRPM the max RPM for the bottom of the shooter (between 5700RPM-1000RPM)
  */
  /*public void setShooterSpeeds(double topRPM, double bottomRPM){
    if(topRPM < 5700 && bottomRPM < 5700 && topRPM > 1000 && bottomRPM > 1000){
    bottomPID.setSmartMotionMaxVelocity(bottomRPM, 0);
    //topPID.setSmartMotionMaxVelocity(topRPM, 0);
    bottomPID.setSmartMotionMinOutputVelocity(bottomRPM-100, 0);
   // topPID.setSmartMotionMinOutputVelocity(topRPM-100, 0);
    bottomPID.setSmartMotionMaxAccel(1500, 0);
   // topPID.setSmartMotionMaxAccel(1500, 0);
    }
    else{
      //System.out.println("Too fast!");
      //topMotor.stopMotor();
      bottomMotor.stopMotor();
    }
  }*/
}
