/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
//import edu.wpi.first.wpilibj.interfaces.Gyro;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.controller.PIDController;

/**
 * The aim of this class is to manage a "hammer" that the design team is building to practice pre-season.
 * The idea is that the A-Button on the DriverController would activate a mode switch in this command, found
 * in the execute() method, and depending on the mode, the hammer would move to (and hopefully maintain, using PID)
 * different positions. There are supposed to be three modes (0, 1, 2). Each time the A-Button is pressed, the
 * mode is incremented. The first it is presed (mode 0), the hammer should move up to 90 degrees horizontally
 * from the bot on a 2-D axis, assuming it is front mounted. I tried to read up a bunch on PID Control Loops
 * and did some impromptu calculus learning, and using some code I found on the WPILib docs, I tried to put
 * together a PID system in this class. Unfortunately, the example used a gyro (I think it was meant to
 * demonstrate PID in a drivebase), so while I am writing this I am also in the middle of trying to figure out
 * if I can use an encoder to get the values I need to input into the PID variables.
 * 
 * If I cannot use encoders, I think this will be a lost cause. I may just have to rely on timers to start and
 * stop the motors. I will also just use different buttons to control the direction of the hammer motors.
 */
public class hammer_command extends Command {
  private boolean astat;                             // is set to status (on/off) of A-Button
  private int hammerMode = 0;                        // configures what will happen when A-Button is pressed
  private Timer bTimer;                              // timer to time how far apart the A-Button is pressed
  private PIDController hpid;
  private double ang = 0;

  private double rcw = 0;                            // the motor input value
  //private int P, I = 1;                              // the gradients of the PID inputs; will multiply with the PID in the PID() function to adjust the values
  //private int P, I, D = 1;                         // the PID inputs; will be multiplied by the gradients in the PID() function
  //private double integral, previous_error, setpoint = 0;// values for PID control
  //private boolean activeSetpoint = true;             // whether the PID should affect the motor input value
  //private Gyro gyro;                               // not doing anything right now, would get the position of the robot, but there is no physical gyro
  // TODO: need to get encoders to work with this, anything to get how stuff is working because there is no gyro

  public hammer_command() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.hammer_subsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.hammerEncoder.reset();
    //Robot.hammerEncoder.setDistancePerPulse(0.0125);// TODO: I HAVE NO IDEA WHAT THIS VALUE SHOULD BE
    bTimer = new Timer();
    bTimer.start();
    SmartDashboard.putString("Hammer Position", "starting position");
    hpid = new PIDController(1, 1, 1, 0);
    //hpid.setIntegratorRange(-1, 1);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //PID();// run the pid repeatedly to adjust to the setpoint

    hpid.enableContinuousInput(0, 90);
    hpid.setIntegratorRange(-1, 1);
    if (Robot.m_oi.buttonA.get() && !astat) {// NOTE: the hammer setting remains at 0 the first time A-Button is pushed
      bTimer.stop();
      if (bTimer.get() > 0.1) {
        if (hammerMode == 0) {
          //activeSetpoint = true;
          //setSetpoint(90);
          hpid.setSetpoint(90);
          hammerMode = 1;
          SmartDashboard.putString("Hammer Position", "up");
        } else if (hammerMode == 1) {
          //activeSetpoint = true;
          //setSetpoint(0);
          hpid.setSetpoint(0);
          hammerMode = 2;
          SmartDashboard.putString("Hammer Position", "power down");
          //Robot.hammer_subsystem.hammerActivate(false);
        } else {
          //activeSetpoint = false;
          //setSetpoint(0);
          hpid.setSetpoint(0);
          hammerMode = 0;
          SmartDashboard.putString("Hammer Position", "down");
        }
      }
      bTimer.reset();
      bTimer.start();
    } else {
      // TODO: add timer listening feature here
      //bTimer.start();
    }
    ang = Robot.hammerEncoder.getRaw() * 0.0125 * 360;
    System.out.println(hpid.calculate(ang));
    SmartDashboard.putNumber("ANG", ang);
    switch (hammerMode) {
      case 0:
        rcw = hpid.calculate(ang);
        break;
      case 1:
        hpid.setSetpoint(0);
        rcw = -1;
        break;
      case 2:
        rcw = hpid.calculate(ang);
        break;
      default:
        rcw = hpid.calculate(ang);
        break;
    }
    Robot.hammer_subsystem.hammerSet(rcw);
    //Robot.hammer_subsystem.hammerActivate();// other idea would be to make it take a boolean that instructs it whether to be active

    astat = Robot.m_oi.buttonA.get();
    SmartDashboard.putBoolean("Hammer Button", astat);
    //System.out.println("A stat:" + astat);
  }

  /*public void setSetpoint(int stpnt) {
    setpoint = stpnt;
  }

  public void PID() {
    if (activeSetpoint) {
      double ang = Robot.hammerEncoder.getRaw() * 0.0125 * 360;
      //System.out.println(Robot.hammerEncoder.getRaw());
      double error = setpoint - ang; // Error = Target - Actual
      integral += (error*.02); // Integral is increased by the error*time (which is .02 seconds using normal IterativeRobot)
      double derivative = (error - previous_error) / .02;
      
      SmartDashboard.putNumber("ANG", ang);
      SmartDashboard.putNumber("ERROR", error);
      SmartDashboard.putNumber("INTEGRAL", integral);
      SmartDashboard.putNumber("DERIVATIVE", derivative);
      SmartDashboard.putNumber("PREV", previous_error);
      
      //rcw = P*error + I*this.integral + D*derivative;
      rcw = P*error + I*this.integral;
      previous_error = setpoint - ang;
    } else {
      rcw = 0;
    }
  }*/

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.hammer_subsystem.hammerSet(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.hammer_subsystem.hammerSet(0);
  }
}
