/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class shooter_command extends Command {
  // double topSpeed;
  //double bottomSpeed;
  //double bottomSpeedSet = 0.8;
  //double maxRPM = 5700;
  //double topSpeedSet = 0.8;
  //private double maxRPM = 5700;
  private boolean shooting = false;
  private double shooterDefault = 0.5;// WASN'T HERE BEFORE MERGE
  private double shooterSpeed = 0;

  // FOR PID CONTROLLER SPARK MAX
  private CANSparkMax m_motor;
  private CANPIDController m_pidController;
  private CANEncoder m_encoder;
  public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM;

  public shooter_command() {
    requires(Robot.shooter_subsystem);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    m_motor = new CANSparkMax(RobotMap.MOTOR_SHOOT_TOP, MotorType.kBrushless);
    m_pidController = m_motor.getPIDController();
    m_encoder = m_motor.getEncoder();

    // PID coefficients
    kP = 6e-5;
    kI = 0.0001;
    kD = 0.00001;
    kIz = 0.00001;
    kFF = 0.0001;
    kMaxOutput = 1;
    kMinOutput = -1;
    maxRPM = 5700;

    // set PID coefficients
    m_pidController.setP(kP);
    m_pidController.setI(kI);
    m_pidController.setD(kD);
    m_pidController.setIZone(kIz);
    m_pidController.setFF(kFF);
    m_pidController.setOutputRange(kMinOutput, kMaxOutput);

    // display PID coefficients on SmartDashboard
    SmartDashboard.putNumber("P Gain", kP);
    SmartDashboard.putNumber("I Gain", kI);
    SmartDashboard.putNumber("D Gain", kD);
    SmartDashboard.putNumber("I Zone", kIz);
    SmartDashboard.putNumber("Feed Forward", kFF);
    SmartDashboard.putNumber("Max Output", kMaxOutput);
    SmartDashboard.putNumber("Min Output", kMinOutput);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    /*SmartDashboard.putNumber("Top RPM", Robot.shooter_subsystem.topEncoder.getVelocity());
    SmartDashboard.putBoolean("Shooting", shooting);
    shooterSpeed = SmartDashboard.getNumber("Shooter Power", shooterDefault);*/
    //SmartDashboard.putNumber("Bottom RPM", Robot.shooter_subsystem.bottomEncoder.getVelocity());
    if(Robot.m_oi.Intake_RB.get() && !shooting){
      //Robot.shooter_subsystem.shooterSet(0, 0.4);
      shooting = true;
    } else if (Robot.m_oi.Intake_LB.get() && shooting) {
      shooting = false;
    }
    double setPoint = 0;
    if (shooting) {
      //Robot.shooter_subsystem.shooterSet(shooterSpeed);
      setPoint = 2000;
    } else {
      //Robot.shooter_subsystem.shooterSet(0);
    }

    // read PID coefficients from SmartDashboard
    double p = SmartDashboard.getNumber("P Gain", 0);
    double i = SmartDashboard.getNumber("I Gain", 0);
    double d = SmartDashboard.getNumber("D Gain", 0);
    double iz = SmartDashboard.getNumber("I Zone", 0);
    double ff = SmartDashboard.getNumber("Feed Forward", 0);
    double max = SmartDashboard.getNumber("Max Output", 0);
    double min = SmartDashboard.getNumber("Min Output", 0);

    // if PID coefficients on SmartDashboard have changed, write new values to
    // controller
    if ((p != kP)) {
      m_pidController.setP(p);
      kP = p;
    }
    if ((i != kI)) {
      m_pidController.setI(i);
      kI = i;
    }
    if ((d != kD)) {
      m_pidController.setD(d);
      kD = d;
    }
    if ((iz != kIz)) {
      m_pidController.setIZone(iz);
      kIz = iz;
    }
    if ((ff != kFF)) {
      m_pidController.setFF(ff);
      kFF = ff;
    }
    if ((max != kMaxOutput) || (min != kMinOutput)) {
      m_pidController.setOutputRange(min, max);
      kMinOutput = min;
      kMaxOutput = max;
    }

    /**
     * PIDController objects are commanded to a set point using the SetReference()
     * method.
     * 
     * The first parameter is the value of the set point, whose units vary depending
     * on the control type set in the second parameter.
     * 
     * The second parameter is the control type can be set to one of four
     * parameters: com.revrobotics.ControlType.kDutyCycle
     * com.revrobotics.ControlType.kPosition com.revrobotics.ControlType.kVelocity
     * com.revrobotics.ControlType.kVoltage
     */
    //double setPoint = m_stick.getY() * maxRPM;
    m_pidController.setReference(setPoint, ControlType.kVelocity);

    SmartDashboard.putNumber("SetPoint", setPoint);
    SmartDashboard.putNumber("ProcessVariable", m_encoder.getVelocity());


    /*else if(Robot.m_oi.Intake_LB.get()){
      Robot.shooter_subsystem.shooterSet(0.4, 0);
    }
    else if(Robot.m_oi.Intake_LB.get() || Robot.m_oi.Intake_RB.get()){
      Robot.shooter_subsystem.shooterSet(0.4, 0.4);
    }
    else if(Robot.m_oi.Intake_buttonA.get()){
      Robot.shooter_subsystem.shooterSet(0.65, 0.65);
    }
    //Partly keeping intake in check (check intake_command with same buttons)
    else if(Robot.m_oi.Intake_buttonY.get()){
      Robot.shooter_subsystem.shooterSet(0.075, 0.1);
    }
    else if(Robot.m_oi.Intake_buttonB.get()){
      Robot.shooter_subsystem.shooterSet(-0.075, -0.1);
    }
    else{
      Robot.shooter_subsystem.shooterSet(0, 0);
    }*/
  }
  @Override
   protected boolean isFinished() {
      return false;
  }

  @Override
  protected void end() {
    //Robot.shooter_subsystem.shooterSet(0); // add back 0
    // VVV CODE IN MERGE CONFLICT
    // Robot.shooter_subsystem.setShooterSpeeds(0, 0);
    //Robot.shooter_subsystem.shootAnyway(0); // add back 0
  }
    // Make this return true when this Command no longer needs to run execute()
  

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
