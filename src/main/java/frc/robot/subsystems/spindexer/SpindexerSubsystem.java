package frc.robot.subsystems.spindexer;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

// Spindexer Subsystem
public class SpindexerSubsystem extends SubsystemBase {

    // Variables
    private final double offset = 0.0;
    private final CANSparkMax motor = new CANSparkMax(4, MotorType.kBrushless);
    private final DutyCycleEncoder encoder = new DutyCycleEncoder(5);

    // In the constructor set the setters
    public SpindexerSubsystem() {
        motor.setSmartCurrentLimit(8);
        motor.setSecondaryCurrentLimit(10);
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

    // In the periodic method of this subsystem, add the encoder distance to smart dashboard
    @Override
    public void periodic() {
        SmartDashboard.putNumber("Spindexer Angle", getDistance());
    }
    
}
