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
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new shooter_command());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
  //Declaring PID and motors for shooter
  public shooter_subsystem(){
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

  }
}
