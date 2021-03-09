package frc.robot.commands.spindexer;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.spindexer.SpindexerSubsystem;

// Spin
public class Spin extends CommandBase {

    // Variables
    private final Timer timer = new Timer();
    private final SpindexerSubsystem subsystem;
    private final double time;

    // Constructor
    public Spin(SpindexerSubsystem subsystem, double time) {
        this.subsystem = subsystem;
        this.time = time;
        addRequirements(subsystem);
    }

    // Initialize method
    @Override
    public void initialize() {
        timer.reset();
        timer.start();
        subsystem.setVoltage(1.0);
    }

    // IsFinished method
    @Override
    public boolean isFinished() {
        return timer.get() >= time;
    }

    // End method
    @Override
    public void end(boolean interrupted) {
        subsystem.setVoltage(0.0);
    }

}