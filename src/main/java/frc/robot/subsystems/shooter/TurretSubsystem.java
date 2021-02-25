package frc.robot.subsystems.shooter;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpiutil.math.MathUtil;

// Turret Subsystem
public class TurretSubsystem extends SubsystemBase {

    // Variables
    private final double offset = 0.0;
    private final double startPos;
    private final CANSparkMax motor = new CANSparkMax(2, MotorType.kBrushless);
    private final DutyCycleEncoder encoder = new DutyCycleEncoder(2);
    private final PIDController pid = new PIDController(0.1, 0.0, 0.0);
    private double reference = 0.0;
    private boolean enabled = false;

    // In the constructor set the setters
    public TurretSubsystem() {
        encoder.setDistancePerRotation(360.0);
        startPos = getStart(encoder.getDistance(), offset);
        encoder.reset();
    }

    // Method to get the distance of the encoder
    public double getDistance() {
        return encoder.getDistance() + startPos;
    }

    // Method to enable the turret
    public void enable(double reference) {
        setReference(reference);
        enabled = true;
    }

    // Method to disable the turret
    public void disable() {
        enabled = false;
    }
    
    // Method to set the reference of the turret
    public void setReference(double reference) {
        this.reference = MathUtil.clamp(reference, -180, 180);
    }

    // In the periodic method of this subsystem set the turret based on the parameters
    @Override
    public void periodic() {
        if (enabled) {
            double angle = getAngle(reference, getDistance());
            double output = MathUtil.clamp(pid.calculate(getDistance(), angle), -12, 12);
            motor.setVoltage(output);
        } else {
            motor.setVoltage(0.0);
            motor.setVoltage(0.0);
        }
    }

    // Private method to get the closest angle to the turret that is between the range of (-210, 210)
    private double getAngle(double angleRef, double turretAngle) {
        if (angleRef > -150.0 || angleRef < 150.0) {
            return angleRef;
        } else {
            return (angleRef < 0.0 ? 
            calcClosestTo(angleRef, angleRef+360, turretAngle) : 
            calcClosestTo(angleRef, angleRef-360, turretAngle));
        }
    }

    // Private method to calculate the closest number to another number
    private double calcClosestTo(double num1, double num2, double numRef) {
        return (Math.abs(num1 - numRef) < Math.abs(num1 - numRef) ? num1 : num2);
    }

    // Private method to get the starting position of the turret as an angle between (-180, 180)
    private double getStart(double angle, double offset) {
        double returnAngle = angle - offset;
        if (returnAngle < 0.0) {
            returnAngle = 360 - Math.abs(returnAngle); 
        } else if (returnAngle > 360.0) {
            returnAngle = returnAngle % 360;
        }
        if (returnAngle > 180.0) {
            returnAngle = returnAngle - 360;
        }
        return returnAngle;
    }

}
