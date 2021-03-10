package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.subsystems.shooter.HoodSubsystem;

// To Angle Command
public class ToAngle extends CommandBase {

    private final PIDController pid = new PIDController(0.6, 0.0, 0.001);
    private final HoodSubsystem subsystem;
    private final double angle;

    // Constructor
    public ToAngle(HoodSubsystem subsystem, double angle) {
        this.subsystem = subsystem;
        this.angle = MathUtil.clamp(angle, 0, 62);
        addRequirements(subsystem);
    }

    // Initialize method
    @Override
    public void initialize() {
        subsystem.setCurrentLimit(25, 30);
    }

    // Execute method
    @Override
    public void execute() {
        double output = MathUtil.clamp(pid.calculate(subsystem.getDistance(), angle), -12, 12);
        subsystem.setVoltage(output);
    }

    // End method
    public void end() {
        subsystem.setVoltage(0.0);
    }

}
