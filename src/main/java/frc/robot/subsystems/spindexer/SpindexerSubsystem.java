package frc.robot.subsystems.spindexer;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

// Spindexer Subsystem
public class SpindexerSubsystem extends SubsystemBase {

    // Variables
    private final double offset = 29.0;
    private final CANSparkMax motor = new CANSparkMax(34, MotorType.kBrushless);
    private final DutyCycleEncoder encoder = new DutyCycleEncoder(0);

    // In the constructor set the setters
    public SpindexerSubsystem() {
        motor.setSmartCurrentLimit(12);
        motor.setSecondaryCurrentLimit(14);
        encoder.setDistancePerRotation(360.0);
    }

    // Method to get the encoder distance
    public double getDistance() {
        return encoder.getDistance() - offset;
    }

    // Method to set the voltage of the spindexer
    public void setVoltage(double voltage) {
        motor.setVoltage(voltage);
    }
    
}
