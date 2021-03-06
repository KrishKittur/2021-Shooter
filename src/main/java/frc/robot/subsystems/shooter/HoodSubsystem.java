
package frc.robot.subsystems.shooter;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

// Hood Subsystem
public class HoodSubsystem extends SubsystemBase {

    // Variables
    private final CANSparkMax motor = new CANSparkMax(31, MotorType.kBrushless);
    private final CANEncoder motorEncoder = motor.getEncoder();
    private final DutyCycleEncoder encoder = new DutyCycleEncoder(2);

    // In the constructor set the setters
    public HoodSubsystem() {
        motor.setSmartCurrentLimit(13);
        motor.setSecondaryCurrentLimit(15);
        encoder.setDistancePerRotation(24.0);
    }

    // Method to get the velocity of the hood encoder
    public double getVelocity() {
        return motorEncoder.getVelocity();
    }

    // Method to get the distance of the hood encoder
    public double getDistance() {
        return encoder.getDistance() - 1.0;
    }

    // Method to set the voltage of the hood encoder
    public void setVoltage(double voltage) {
        motor.setVoltage(-voltage);
    }

    // Method to reset the hood encoder (for the homing routine)
    public void reset() {
        encoder.reset();
    }
    
}