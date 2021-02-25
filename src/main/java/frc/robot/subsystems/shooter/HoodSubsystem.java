package frc.robot.subsystems.shooter;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

// Hood Subsystem
public class HoodSubsystem extends SubsystemBase {

    // Variables
    private final CANSparkMax motor = new CANSparkMax(3, MotorType.kBrushless);
    private final Encoder encoder = new Encoder(3, 4);

    // In the constructor set the setters
    public HoodSubsystem() {
        motor.setSmartCurrentLimit(13);
        motor.setSecondaryCurrentLimit(15);
        encoder.setDistancePerPulse(24.0/2048.0);
    }

    // Method to get the velocity of the hood encoder
    public double getVelocity() {
        return encoder.getRate();
    }

    // Method to get the distance of the hood encoder
    public double getDistance() {
        return encoder.getDistance() - 1.0;
    }

    // Method to set the voltage of the hood encoder
    public void setVoltage(double voltage) {
        motor.setVoltage(voltage);
    }

    // Method to reset the hood encoder (for the homing routine)
    public void reset() {
        encoder.reset();
    }
    
}
