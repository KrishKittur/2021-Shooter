package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.shooter.HoodSubsystem;

// Home Hood Command
public class HomeHoodCommand extends CommandBase {

    // Variables
    HoodSubsystem hoodSubsystem;
    Timer timer;

    // In the constructor set the variables
    public HomeHoodCommand(HoodSubsystem hoodSubsystem) {
        this.hoodSubsystem = hoodSubsystem;
        addRequirements(hoodSubsystem);

        timer = new Timer();
        timer.start();
    }

    // In the initialize method set the hood to move down at a slow rate
    @Override
    public void initialize() {
        hoodSubsystem.setVoltage(-3.0);
    }

    // If it has been 0.25 seconds and the hood is not moving then the command is over
    @Override
    public boolean isFinished() {
        return Math.abs(hoodSubsystem.getVelocity()) < 10.0 && timer.get() >= 0.25;
    }

    // In the end method set the hood to 0
    @Override
    public void end(boolean interrupted) {
        hoodSubsystem.setVoltage(0.0);
        hoodSubsystem.reset();
    }

    
}
