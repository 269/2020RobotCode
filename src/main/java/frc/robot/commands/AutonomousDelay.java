package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonomousDelay extends Command {
    Timer timer = new Timer();
    double seconds;

    public AutonomousDelay(double seconds) {
        this.seconds = seconds;
    }

    protected void initialize() {
        timer.reset();
        timer.start();
    }

    protected void execute() {

    }

    protected boolean isFinished() {
        return timer.get() >= seconds;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}