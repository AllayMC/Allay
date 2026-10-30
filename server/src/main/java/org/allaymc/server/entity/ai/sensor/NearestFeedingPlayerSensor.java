package org.allaymc.server.entity.ai.sensor;

import org.allaymc.api.entity.ai.memory.MemoryTypes;
import org.allaymc.api.entity.ai.sensor.Sensor;
import org.allaymc.api.entity.interfaces.EntityAnimal;
import org.allaymc.api.entity.interfaces.EntityIntelligent;
import org.allaymc.api.entity.interfaces.EntityPlayer;

/**
 * {@code NearestFeedingPlayerSensor} records the nearest eligible player
 * runtime ID for behavior memories. Candidates come from controlled player
 * entities in the current dimension, excluding absent, dead, and
 * phantom-controlled entities. Feeding eligibility additionally requires an
 * animal and a held breeding item accepted by that animal.
 *
 * <p>
 * Selection compares squared three-dimensional distances and replaces the
 * current candidate only for a strictly smaller distance, leaving
 * equal-distance ties in collection iteration order. A scan with no match
 * writes an explicit {@code null}, suppressing any previous stored target until
 * another scan selects a player. A non-animal input returns without updating
 * memory.
 *
 * @author daoge_cmd
 */
public class NearestFeedingPlayerSensor implements Sensor {

    /**
     * Maximum sensing radius in blocks.
     */
    protected final double range;
    /**
     * Sensing interval in group ticks.
     */
    protected final int period;

    /**
     * Creates a sensor retaining the supplied radii and sensing interval
     * without validation. Radius comparisons square the values during sensing,
     * while the behavior group treats nonpositive periods as due every tick.
     */
    public NearestFeedingPlayerSensor(double range, int period) {
        this.range = range;
        this.period = period;
    }

    /**
     * Creates a sensor scanning through the supplied maximum radius every group
     * tick. Only animal inputs update feeding memory. The constructor retains
     * the radius without sign validation before squared-distance checks.
     */
    public NearestFeedingPlayerSensor(double range) {
        this(range, 1);
    }

    /**
     * Stores the nearest eligible player's runtime ID or an explicit {@code
     * null}. Dead, absent, and phantom-controlled player entities are skipped
     * before distance selection. A feeding scan also requires an animal and a
     * held item accepted for breeding.
     *
     * <p>
     * The first candidate at a given minimum distance remains selected because
     * later candidates must be strictly nearer. Non-animal inputs leave feeding
     * memory untouched. A failed scan replaces the prior ID rather than
     * retaining stale observations, and memory consumers resolve the selected
     * runtime ID separately.
     */
    @Override
    public void sense(EntityIntelligent entity) {
        if (!(entity instanceof EntityAnimal animal)) {
            return;
        }

        var loc = entity.getLocation();
        // Compare squared world distances to avoid square roots during candidate selection.
        double rangeSq = range * range;

        EntityPlayer nearest = null;
        double nearestDistSq = Double.MAX_VALUE;

        for (var player : entity.getDimension().getPlayers()) {
            var playerEntity = player.getControlledEntity();
            if (playerEntity == null || playerEntity.isDead() || playerEntity.isPhantom()) continue;

            double distSq = loc.distanceSquared(playerEntity.getLocation());
            if (distSq > rangeSq) continue;

            var itemInHand = playerEntity.getItemInHand();
            if (itemInHand == null || !animal.isBreedingItem(itemInHand)) continue;

            if (distSq < nearestDistSq) {
                nearestDistSq = distSq;
                nearest = playerEntity;
            }
        }

        entity.getMemoryStorage().put(MemoryTypes.NEAREST_FEEDING_PLAYER, nearest != null ? nearest.getRuntimeId() : null);
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
