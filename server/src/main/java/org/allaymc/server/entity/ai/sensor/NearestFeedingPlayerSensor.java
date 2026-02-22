package org.allaymc.server.entity.ai.sensor;

import org.allaymc.api.entity.ai.memory.MemoryTypes;
import org.allaymc.api.entity.ai.sensor.Sensor;
import org.allaymc.api.entity.interfaces.EntityAnimal;
import org.allaymc.api.entity.interfaces.EntityIntelligent;
import org.allaymc.api.entity.interfaces.EntityPlayer;

/**
 * Scans for the nearest player holding a breeding item for this animal.
 *
 * @author daoge_cmd
 */
public class NearestFeedingPlayerSensor implements Sensor {

    protected final double range;
    protected final int period;

    public NearestFeedingPlayerSensor(double range, int period) {
        this.range = range;
        this.period = period;
    }

    public NearestFeedingPlayerSensor(double range) {
        this(range, 1);
    }

    @Override
    public void sense(EntityIntelligent entity) {
        if (!(entity instanceof EntityAnimal animal)) {
            return;
        }

        var loc = entity.getLocation();
        double rangeSq = range * range;

        EntityPlayer nearest = null;
        double nearestDistSq = Double.MAX_VALUE;

        for (var player : entity.getDimension().getPlayers()) {
            var playerEntity = player.getControlledEntity();
            if (playerEntity == null || playerEntity.isDead()) continue;

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

    @Override
    public int getPeriod() {
        return period;
    }
}
