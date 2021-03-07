package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.shooter.TurretSubsystem;

public class ToAngle extends CommandBase {

    private final TurretSubsystem subsystem;
    private final double angle;

    public ToAngle(TurretSubsystem subsystem, double angle) {
        this.subsystem = subsystem;
        addRequirements(subsystem);
        this.angle = angle;
    }

    @Override
    public void initialize() {
        subsystem.enable(angle);
    }

    @Override
    public void execute() {
        subsystem.setReference(angle);
    }

    @Override
    public void end(boolean interrupted) {
        subsystem.disable();
    }
    
}
