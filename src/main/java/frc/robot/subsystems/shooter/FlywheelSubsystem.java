package frc.robot.subsystems.shooter;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpiutil.math.MathUtil;

// Shooter subsystem
public class FlywheelSubsystem extends SubsystemBase {
    
    // Variables
    private final CANSparkMax rightMotor = new CANSparkMax(0, MotorType.kBrushless);
    private final CANSparkMax leftMotor = new CANSparkMax(1, MotorType.kBrushless);
    private final Encoder encoder = new Encoder(0, 1);
    private final PIDController pid = new PIDController(0.01, 0.0, 0.0);
    private final SimpleMotorFeedforward ff = new SimpleMotorFeedforward(0.3, 0.002);
    private double reference = 0.0;
    private boolean enabled = false;

    // In the constructor set the setters
    public FlywheelSubsystem() {
        encoder.setDistancePerPulse(1.0/2048.0);
    }
    
    // Method to get the rpm of the flywheel
    public double getRPM() {
        return encoder.getRate() * 60.0;
    }

    // Method to enable the flywheel
    public void enable(double reference) {
        this.reference = reference;
        enabled = true;
    }

    // Method to disabled the flywheel
    public void disable() {
        enabled = false;
    }

    // Method to set the reference for the flywheel
    public void setReference(double reference) {
        this.reference = reference;
    }
    
    // In the periodic method of this subsystem set the flywheel based on the parameters
    @Override
    public void periodic() {
        if (enabled) {
            double pidOutput = pid.calculate(getRPM(), reference);
            double ffOutput = ff.calculate(reference);
            double output = MathUtil.clamp(pidOutput + ffOutput, -12, 12);
            rightMotor.setVoltage(output);
            leftMotor.setVoltage(output);
        } else {
            rightMotor.setVoltage(0.0);
            leftMotor.setVoltage(0.0);
        }
    }

}
