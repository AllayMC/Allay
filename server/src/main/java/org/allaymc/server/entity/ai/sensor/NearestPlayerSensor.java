package org.allaymc.server.entity.ai.sensor;

import org.allaymc.api.entity.ai.memory.MemoryTypes;
import org.allaymc.api.entity.ai.sensor.Sensor;
import org.allaymc.api.entity.interfaces.EntityIntelligent;
import org.allaymc.api.entity.interfaces.EntityPlayer;

/**
 * {@code NearestPlayerSensor} records the nearest eligible player runtime ID
 * for behavior memories. Candidates come from controlled player entities in the
 * current dimension, excluding absent, dead, and phantom-controlled entities.
 * The supplied minimum and maximum radii define an inclusive distance band.
 *
 * <p>
 * Selection compares squared three-dimensional distances and replaces the
 * current candidate only for a strictly smaller distance, leaving
 * equal-distance ties in collection iteration order. A scan with no match
 * writes an explicit {@code null}, suppressing any previous stored target until
 * another scan selects a player. The sensor period controls when the behavior
 * group invokes another scan.
 *
 * @author daoge_cmd
 */
public class NearestPlayerSensor implements Sensor {

    /**
     * Maximum sensing radius in blocks.
     */
    protected final double range;
    /**
     * Minimum sensing radius in blocks.
     */
    protected final double minRange;
    /**
     * Sensing interval in group ticks.
     */
    protected final int period;

    /**
     * Creates a sensor retaining the supplied radii and sensing interval
     * without validation. Radius comparisons square the values during sensing,
     * while the behavior group treats nonpositive periods as due every tick.
     */
    public NearestPlayerSensor(double range, double minRange, int period) {
        this.range = range;
        this.minRange = minRange;
        this.period = period;
    }

    /**
     * Creates a sensor scanning through the supplied maximum radius every group
     * tick. The minimum radius defaults to zero. The constructor retains the
     * radius without sign validation before squared-distance checks.
     */
    public NearestPlayerSensor(double range) {
        this(range, 0, 1);
    }

    /**
     * Stores the nearest eligible player's runtime ID or an explicit {@code
     * null}. Dead, absent, and phantom-controlled player entities are skipped
     * before distance selection. Distance must fall within the inclusive
     * minimum and maximum squared radii.
     *
     * <p>
     * The first candidate at a given minimum distance remains selected because
     * later candidates must be strictly nearer. The check uses
     * three-dimensional world distance rather than horizontal distance alone. A
     * failed scan replaces the prior ID rather than retaining stale
     * observations, and memory consumers resolve the selected runtime ID
     * separately.
     */
    @Override
    public void sense(EntityIntelligent entity) {
        var loc = entity.getLocation();
        // Compare squared world distances to avoid square roots during candidate selection.
        double rangeSq = range * range;
        double minRangeSq = minRange * minRange;

        EntityPlayer nearest = null;
        double nearestDistSq = Double.MAX_VALUE;

        for (var player : entity.getDimension().getPlayers()) {
            var playerEntity = player.getControlledEntity();
            if (playerEntity == null || playerEntity.isDead() || playerEntity.isPhantom()) continue;

            double distSq = loc.distanceSquared(playerEntity.getLocation());
            if (distSq < minRangeSq || distSq > rangeSq) continue;

            if (distSq < nearestDistSq) {
                nearestDistSq = distSq;
                nearest = playerEntity;
            }
        }

        entity.getMemoryStorage().put(MemoryTypes.NEAREST_PLAYER, nearest != null ? nearest.getRuntimeId() : null);
    }

    /**
     * Returns the configured group-tick interval between this sensor's
     * observation scans.
     */
    @Override
    public int getPeriod() {
        return period;
    }
}
