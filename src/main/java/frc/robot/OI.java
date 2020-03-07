/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //the xbox controllers considered a "Joystick" datatype
    public Joystick driverController = new Joystick(RobotMap.DRIVER_CONTROLLER);
    public Joystick intakeController = new Joystick(RobotMap.INTAKE_CONTROLLER);
    /**
     * @param controller The Controller(Joystick datatype) of wich you want to retrive the value from.
     * @return X-Axis postion from -1.0 to 1.0
     */
    public double leftJoystickX(Joystick controller){
        return controller.getRawAxis(RobotMap.LEFT_JOYSTICK_X);
    }
    /**
     * @param controller The Controller(Joystick datatype) of wich you want to retrive the value from.
     * @return Y-Axis postion from -1.0 to 1.0
     */
    public double leftJoystickY(Joystick controller){
        return controller.getRawAxis(RobotMap.LEFT_JOYSTICK_Y);
    }
    /**
     * @param controller The Controller(Joystick datatype) of wich you want to retrive the value from.
     * @return X-Axis postion from -1.0 to 1.0
     */
    public double rightJoystickX(Joystick controller){
        return controller.getRawAxis(RobotMap.RIGHT_JOYSTICK_X);
    }
    /**
     * @param controller The Controller(Joystick datatype) of wich you want to retrive the value from.
     * @return Y-Axis postion from -1.0 to 1.0
     */
    public double rightJoystickY(Joystick controller){
        return controller.getRawAxis(RobotMap.RIGHT_JOYSTICK_Y);
    }

    //Driver Controller Buttons
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
    
    //Intake Controller Buttons
    public JoystickButton Intake_buttonX = new JoystickButton(intakeController,RobotMap.BUTTON_X);
    public JoystickButton Intake_buttonY = new JoystickButton(intakeController,RobotMap.BUTTON_Y);
    public JoystickButton Intake_buttonA = new JoystickButton(intakeController,RobotMap.BUTTON_A);    
    public JoystickButton Intake_buttonB = new JoystickButton(intakeController,RobotMap.BUTTON_B);    
    public JoystickButton Intake_startButton = new JoystickButton(intakeController,RobotMap.START_BUTTON);  
    public JoystickButton Intake_selectButton = new JoystickButton(intakeController,RobotMap.SELECT_BUTTON);
    public JoystickButton Intake_dPadLeft = new JoystickButton(intakeController,RobotMap.D_PAD_LEFT);
    public JoystickButton Intake_dPadRight = new JoystickButton(intakeController,RobotMap.D_PAD_RIGHT);    
    public JoystickButton Intake_dPadUp = new JoystickButton(intakeController,RobotMap.D_PAD_UP);    
    public JoystickButton Intake_dPadDown = new JoystickButton(intakeController,RobotMap.D_PAD_DOWN);    
    public JoystickButton Intake_LB = new JoystickButton(intakeController,RobotMap.LB);    
    public JoystickButton Intake_RB = new JoystickButton(intakeController,RobotMap.RB);  

    
    //POV Buttons
    public POVButton Driver_upButton = new POVButton(driverController, 0, RobotMap.UP_BUTTON);
    public POVButton Driver_rightButton = new POVButton(driverController, 90, RobotMap.RIGHT_BUTTON);
    public POVButton Driver_downButton = new POVButton(driverController, 180, RobotMap.DOWN_BUTTON);
    public POVButton Driver_leftButton = new POVButton(driverController, 270, RobotMap.LEFT_BUTTON);
    
    public POVButton Intake_upButton = new POVButton(intakeController, 0, RobotMap.UP_BUTTON);
    public POVButton Intake_rightButton = new POVButton(intakeController, 90, RobotMap.RIGHT_BUTTON);
    public POVButton Intake_downButton = new POVButton(intakeController, 180, RobotMap.DOWN_BUTTON);
    public POVButton Intake_leftButton = new POVButton(intakeController, 270, RobotMap.LEFT_BUTTON);

    /**
     * A custom method we made last year to bind the button commands early
     */
    public void bind() {
         //// TRIGGERING COMMANDS WITH BUTTONS
            // Once you have a button, it's trivial to bind it to a button in one of
            // three ways:

            // Start the command when the button is pressed and let it run the command
            // until it is finished as determined by it's isFinished method.
            // button.whenPressed(new ExampleCommand());

            // Run the command while the button is being held down and interrupt it once
            // the button is released.
            // button.whileHeld(new ExampleCommand());

            // Start the command when the button is released and let it run the command
            // until it is finished as determined by it's isFinished method.
            // button.whenReleased(new ExampleCommand());
    }

}

