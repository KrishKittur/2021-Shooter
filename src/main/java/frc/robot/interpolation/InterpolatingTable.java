package frc.robot.interpolation;

import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;


import static java.util.Map.entry;

public class InterpolatingTable {

    private InterpolatingTable() {}

    private static final TreeMap<Double, ShotParameter> map = new TreeMap<>(
        Map.ofEntries(
            entry(0.0, new ShotParameter(50.0, 2000.0, -1.0)),
            entry(1.0, new ShotParameter(51.0, 2500.0, -2.0)),
            entry(2.0, new ShotParameter(52.0, 3000.0, 0.0)),
            entry(3.0, new ShotParameter(53.0, 3250.0, -4.0)),
            entry(4.0, new ShotParameter(54.0, 3750.0, -2.0)),
            entry(5.0, new ShotParameter(55.0, 4000.0, -1.0))
        )
    );

    public static ShotParameter get(double distance) {
        Entry<Double, ShotParameter> ceilEntry = map.ceilingEntry(distance);
        Entry<Double, ShotParameter> floorEntry = map.floorEntry(distance);
        if (ceilEntry == null) return floorEntry.getValue();
        if (floorEntry == null) return ceilEntry.getValue();
        if (ceilEntry.getValue().equals(floorEntry.getValue())) return ceilEntry.getValue();
        return ceilEntry.getValue().interpolate(
            floorEntry.getValue(), 
            (distance - floorEntry.getKey())/(ceilEntry.getKey() - floorEntry.getKey())
        );
    }

}