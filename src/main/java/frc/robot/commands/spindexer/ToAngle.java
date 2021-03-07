package frc.robot.commands.spindexer;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.subsystems.spindexer.SpindexerSubsystem;

// To Angle Command
public class ToAngle extends CommandBase {

    // Variables
    private final PIDController pid = new PIDController(0.01, 0.0, 0.0);
    private final SpindexerSubsystem subsystem;
    private final double reference;
    
    // Constructor
    public ToAngle(SpindexerSubsystem subsystem) {
        this.subsystem = subsystem;
        this.reference = getAngle(subsystem.getDistance());
        pid.setTolerance(3, 1);
    }

    // Execute method
    @Override
    public void execute() {
        double output = MathUtil.clamp(pid.calculate(subsystem.getDistance(), reference), -6, 6);
        subsystem.setVoltage(output);
    }

    // Method to calculate the setpoint for this command
    public static double getAngle(double currAngle) {
        double remainder = currAngle % 72;
        return currAngle >= 0.0 ? currAngle - remainder : (currAngle - remainder) - 72;
    }


    
}
