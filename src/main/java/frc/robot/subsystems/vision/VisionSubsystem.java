package frc.robot.subsystems.vision;

import org.photonvision.PhotonCamera;
import org.photonvision.PhotonPipelineResult;
import org.photonvision.PhotonTrackedTarget;
import org.photonvision.PhotonUtils;

import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

// Vision Susbystem
public class VisionSubsystem extends SubsystemBase {

    // Variables
    private final PhotonCamera camera = new PhotonCamera("BREAD_Camera");
    private double yaw = 0.0;
    private double pitch = 0.0;
    private double distance = 0.0;

    // Method to get yaw
    public double getYaw() {
        return yaw;
    }

    // Method to get pitch
    public double getPitch() {
        return pitch;
    }

    // Method to get distance
    public double getDistance() {
        return distance;
    }

    // In the periodic method up this subsystem update the yaw and the pitch
    @Override
    public void periodic() {
        PhotonPipelineResult result = camera.getLatestResult();
        if (result.hasTargets()) {
            PhotonTrackedTarget target = result.getBestTarget();
            yaw = target.getYaw();
            pitch = target.getPitch();
            distance = PhotonUtils.calculateDistanceToTargetMeters(
                1.2065, 
                2.4892, 
                Units.degreesToRadians(23.0), 
                pitch
            );
        }
    }

    
}
