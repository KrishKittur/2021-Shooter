package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.subsystems.shooter.HoodSubsystem;

// To Angle Command
public class ToAngle extends CommandBase {

    private final PIDController pid = new PIDController(0.01, 0.0, 0.0);
    private final HoodSubsystem subsystem;
    private final double angle;

    // Constructor
    public ToAngle(HoodSubsystem subsystem, double angle) {
        this.subsystem = subsystem;
        this.angle = MathUtil.clamp(angle, 0, 62);
        addRequirements(subsystem);
    }

    // Execute method
    @Override
    public void initialize() {
        double output = MathUtil.clamp(pid.calculate(subsystem.getDistance(), angle), -6, 6);
        subsystem.setVoltage(output);
    }

    // End method
    public void end() {
        subsystem.setVoltage(0.0);
    }

}
