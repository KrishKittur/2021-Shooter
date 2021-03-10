package frc.robot.subsystems.shooter;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

// Hood Subsystem
public class HoodSubsystem extends SubsystemBase {

    // Variables
    private final CANSparkMax motor = new CANSparkMax(31, MotorType.kBrushless);
    private final CANEncoder motorEncoder = motor.getEncoder();
    private final DutyCycleEncoder encoder = new DutyCycleEncoder(2);

    // In the constructor set the setters
    public HoodSubsystem() {
        setCurrentLimit(13, 15);
        encoder.setDistancePerRotation(24.0);
    }

    // Method to get the velocity of the hood encoder
    public double getVelocity() {
        return motorEncoder.getVelocity();
    }

    // Method to get the distance of the hood encoder
    public double getDistance() {
        return -encoder.getDistance() - 1.0;
    }

    // Method to set the voltage of the hood encoder
    public void setVoltage(double voltage) {
        motor.setVoltage(-voltage);
    }

    // Method to reset the hood encoder (for the homing routine)
    public void reset() {
        encoder.reset();
    }

    // Method to set the current limit of the motor 
    public void setCurrentLimit(int smart, double secondary) {
        motor.setSmartCurrentLimit(smart);
        motor.setSecondaryCurrentLimit(secondary);
    }
    
    // In the periodic method of this subsystem, write to smart dashboard
    @Override
    public void periodic() {
        SmartDashboard.putNumber("Hood Angle", getDistance());
    }
    
}
