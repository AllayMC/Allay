package org.allaymc.server.entity.ai.sensor;

import org.allaymc.api.entity.ai.memory.MemoryTypes;
import org.allaymc.api.entity.ai.sensor.Sensor;
import org.allaymc.api.entity.interfaces.EntityIntelligent;
import org.allaymc.api.entity.interfaces.EntityPlayer;

/**
 * Scans for the nearest player within range and stores it in memory.
 *
 * @author daoge_cmd
 */
public class NearestPlayerSensor implements Sensor {

    protected final double range;
    protected final double minRange;
    protected final int period;

    public NearestPlayerSensor(double range, double minRange, int period) {
        this.range = range;
        this.minRange = minRange;
        this.period = period;
    }

    public NearestPlayerSensor(double range) {
        this(range, 0, 1);
    }

    @Override
    public void sense(EntityIntelligent entity) {
        var loc = entity.getLocation();
        double rangeSq = range * range;
        double minRangeSq = minRange * minRange;

        EntityPlayer nearest = null;
        double nearestDistSq = Double.MAX_VALUE;

        for (var player : entity.getDimension().getPlayers()) {
            var playerEntity = player.getControlledEntity();
            if (playerEntity == null || playerEntity.isDead()) continue;

            double distSq = loc.distanceSquared(playerEntity.getLocation());
            if (distSq < minRangeSq || distSq > rangeSq) continue;

            if (distSq < nearestDistSq) {
                nearestDistSq = distSq;
                nearest = playerEntity;
            }
        }

        entity.getMemoryStorage().put(MemoryTypes.NEAREST_PLAYER, nearest != null ? nearest.getRuntimeId() : null);
    }

    @Override
    public int getPeriod() {
        return period;
    }
}
