/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import edu.wpi.first.wpilibj.I2C;

/**
 The_Whole_Robot_Code_Trust_Me_I_Am_Always_Right_269_2020_Squire_Grace_Forever
 */
public class RobotMap {

    public static final boolean DEBUG = false;  //use this to toggle your system.print line statements
    public static final int DEBUGLVL = 0;
    /* ******************
     *  DEBUGLVL INFO
     * ******************
     * Anything with a specified debug level thats smaller then the int here will be logged.
     * Levels are as follows: 0-5 (0=log nothing, 1=CRITICAL, 2=Errors, 3=Warnings, 4=Info, 5=everything)
     * This is just an example. Adjust this to meet your requirements. Other implemntation idea could be assign a
     *  number to each component you want to test. i.e. log only gyro or log only drive or log gyo and drive..
     */

    //Drive Motors
    public static final int MOTOR_LEFT_1 = 1;
    public static final int MOTOR_LEFT_2 = 2;
    public static final int MOTOR_RIGHT_1 = 3;
    public static final int MOTOR_RIGHT_2 = 4;
    //Intake Motor
    public static final int MOTOR_INTAKE = 5;
    //Shooter Motor
    public static final int MOTOR_SHOOT_TOP = 1;
    public static final int MOTOT_SHOOT_BOTTOM = 2;
    //SOLENOID
    public static final int SOLENOID_1 = 10;
    public static final int SOLENOID_2 = 11;
    //AIR COMPRESSOR
    public static final int AIRCOMP = 0;
    //COLOR WHEEL
    public static final int MOTOR_COLOR_WHEEL = 4;
    //Controllers
    public static final int DRIVER_CONTROLLER = 0;
    public static final int INTAKE_CONTROLLER = 1;
    //Joysticks
    public static final int LEFT_JOYSTICK_X = 0;
    public static final int LEFT_JOYSTICK_Y = 1;
    public static final int RIGHT_JOYSTICK_X = 4;
    public static final int RIGHT_JOYSTICK_Y = 5;
    //Buttons
    public static final int BUTTON_X = 3;
    public static final int BUTTON_Y = 2;
    public static final int BUTTON_A = 1;
    public static final int BUTTON_B = 4;
    public static final int D_PAD_LEFT = 5;
    public static final int D_PAD_UP = 6;
    public static final int D_PAD_DOWN = 7;
    public static final int D_PAD_RIGHT = 8;
    public static final int START_BUTTON = 9;
    public static final int SELECT_BUTTON = 10;

    //Triggers
    public static final int LT = 0;
    public static final int RT = 1;
    //Bumpers
    public static final int LB = 6;
    public static final int RB = 5;
    
    //Encoders
    public static final int leftEncoderPort1 = 3;
	public static final int leftEncoderPort2 = 2;
	public static final int rightEncoderPort1 = 0;
	public static final int rightEncoderPort2 = 1;

    //POV Buttons
    public static final int UP_BUTTON = 0;
    public static final int RIGHT_BUTTON = 2;
    public static final int DOWN_BUTTON = 4;
    public static final int LEFT_BUTTON = 6;
    
    //color sensor
    public static final I2C.Port i2cPort = I2C.Port.kOnboard;

    //lift motor
    public static final int LIFTMOTOR = 7;
}
