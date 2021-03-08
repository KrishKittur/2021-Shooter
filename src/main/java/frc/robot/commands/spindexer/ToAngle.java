package frc.robot.commands.spindexer;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.subsystems.spindexer.SpindexerSubsystem;

// To Angle Command
public class ToAngle extends CommandBase {

    // Variables
    private final SpindexerSubsystem subsystem;
    private final PIDController pid = new PIDController(0.1, 0.0, 0.001);
    private double reference;
    
    // Constructor
    public ToAngle(SpindexerSubsystem subsystem) {
        this.subsystem = subsystem;
        pid.setTolerance(3, 1);
    }

    // Initialize method
    @Override
    public void initialize() {
        reference = getAngle(subsystem.getDistance());
    }

    // Execute method
    @Override
    public void execute() {
        subsystem.setVoltage(3);
    }

    // IsFinished method
    @Override
    public boolean isFinished() {
        return false;
    }

    // End method
    @Override
    public void end(boolean interrupted) {
        subsystem.setVoltage(0.0);
        System.out.println(interrupted);
    }

    // Method to calculate the setpoint for this command
    public static double getAngle(double currAngle) {
        double remainder = currAngle % 72;
        return currAngle >= 0.0 ? (currAngle - remainder) + 72 : currAngle - remainder;
    }


    
}
