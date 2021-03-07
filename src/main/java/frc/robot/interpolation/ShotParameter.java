package frc.robot.interpolation;

public class ShotParameter {

    public final double hoodAngle;
    public final double rpm;
    public final double offset;

    public ShotParameter(double hoodAngle, double rpm, double offset) {
        this.hoodAngle = hoodAngle;
        this.rpm = rpm;
        this.offset = offset;
    }   

    public boolean equals(ShotParameter other) {
        return Math.abs(this.hoodAngle - other.hoodAngle) < 0.1 &&
        Math.abs(this.rpm - other.rpm) < 0.1 &&
        Math.abs(this.offset - other.offset) < 0.1;
    }

    public ShotParameter interpolate(ShotParameter end, double t) {
        return new ShotParameter(
            lerp(hoodAngle, end.hoodAngle, t), 
            lerp(rpm, end.rpm, t), 
            lerp(offset, end.offset, t)
        );
    }

    private double lerp(double y2, double y1, double t) {
        return y1 + (t * (y2 - y1));
    }
 
}
