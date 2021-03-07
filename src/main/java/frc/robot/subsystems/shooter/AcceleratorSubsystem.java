package frc.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.TalonFXSensorCollection;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpiutil.math.MathUtil;

// Accelerator Subsystem
public class AcceleratorSubsystem extends SubsystemBase {

    // Variables
    private final WPI_TalonFX motor = new WPI_TalonFX(5);
    private final TalonFXSensorCollection sensorCollection = motor.getSensorCollection();
    private final SimpleMotorFeedforward ff = new SimpleMotorFeedforward(0.3, 0.002);
    private final PIDController pid = new PIDController(0.1, 0, 0);
    private double reference = 0.0;
    private boolean enabled = false;

    // Method to get the rpm of the accelerator
    public double getRPM() {
        return sensorCollection.getIntegratedSensorVelocity() * 1.0/2048.0 * 600.0;
    }

    // Method to enable the accelerator
    public void enable(double reference) {
        setReference(reference);
        enabled = true;
    }

    // Method to disabled the accelerator
    public void disable() {
        enabled = false;
    }

    // Method to set the reference of the accelerator
    public void setReference(double reference) {
        this.reference = reference;
    }

    // Method to check whether you are at the reference
    public boolean atReference() {
        return pid.atSetpoint();
    }

    // In the periodic method of this subsystem set the accelerator based on the parameters
    @Override
    public void periodic() {
        if (enabled) {
            double pidOutput = pid.calculate(getRPM(), reference);
            double ffOutput = ff.calculate(reference);
            double output = MathUtil.clamp(pidOutput + ffOutput, -12, 12);
            motor.setVoltage(output);
        } else {
            motor.setVoltage(0.0);
        }
    }
}
