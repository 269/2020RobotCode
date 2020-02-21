/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
//Digital
public class OI {
    //this is getting the left joystick's x axis
    public double leftJoystickX(Joystick controller){
        return controller.getRawAxis(RobotMap.LEFT_JOYSTICK_X);
    }
    public double leftJoystickY(Joystick controller){
        return controller.getRawAxis(RobotMap.LEFT_JOYSTICK_Y);
    }
    public double rightJoystickX(Joystick controller){
        return controller.getRawAxis(RobotMap.RIGHT_JOYSTICK_X);
    }
    public double rightJoystickY(Joystick controller){
        return controller.getRawAxis(RobotMap.RIGHT_JOYSTICK_Y);
    }
    public Joystick driverController = new Joystick(RobotMap.DRIVER_CONTROLLER);
    public Joystick intakeController = new Joystick(RobotMap.INTAKE_CONTROLLER);
    public JoystickButton buttonX = new JoystickButton(driverController,RobotMap.BUTTON_X);
    public JoystickButton buttonY = new JoystickButton(driverController,RobotMap.BUTTON_Y);
    public JoystickButton buttonA = new JoystickButton(driverController,RobotMap.BUTTON_A);    
    public JoystickButton buttonB = new JoystickButton(driverController,RobotMap.BUTTON_B);    
    public JoystickButton startButton = new JoystickButton(driverController,RobotMap.START_BUTTON);  
    public JoystickButton selectButton = new JoystickButton(driverController,RobotMap.SELECT_BUTTON);
    public JoystickButton dPadLeft = new JoystickButton(driverController,RobotMap.D_PAD_LEFT);
    public JoystickButton dPadRight = new JoystickButton(driverController,RobotMap.D_PAD_RIGHT);    
    public JoystickButton dPadUp = new JoystickButton(driverController,RobotMap.D_PAD_UP);    
    public JoystickButton dPadDown = new JoystickButton(driverController,RobotMap.D_PAD_DOWN);    
    public JoystickButton LB = new JoystickButton(driverController,RobotMap.LB);    
    public JoystickButton RB = new JoystickButton(driverController,RobotMap.RB);   

    public JoystickButton buttonA1 = new JoystickButton(intakeController,RobotMap.BUTTON_A);    
    public JoystickButton buttonX1 = new JoystickButton(intakeController, RobotMap.BUTTON_X);
    public JoystickButton buttonY1 = new JoystickButton(intakeController, RobotMap.BUTTON_Y);
    public JoystickButton dPadUpInt = new JoystickButton(intakeController, RobotMap.D_PAD_UP);
    public JoystickButton dPadRightInt = new JoystickButton(intakeController, RobotMap.D_PAD_RIGHT);
    public JoystickButton dPadLeftInt = new JoystickButton(intakeController, RobotMap.D_PAD_LEFT);
    public JoystickButton RBInt = new JoystickButton(intakeController, RobotMap.RB);
}
