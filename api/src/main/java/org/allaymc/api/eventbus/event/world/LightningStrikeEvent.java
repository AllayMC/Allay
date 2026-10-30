package org.allaymc.api.eventbus.event.world;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityLightningBolt;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.world.Dimension;

/**
 * {@code LightningStrikeEvent} represents a proposed lightning bolt in a
 * particular dimension, together with its reported cause. The dimension
 * dispatches the event after creating the entity and before requesting its
 * addition; cancellation prevents that addition through the strike operation.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.DIMENSION)
public class LightningStrikeEvent extends WorldEvent implements CancellableEvent {

    /**
     * {@code Cause} identifies the source reported for a proposed lightning
     * strike.
     */
    public enum Cause {
        /**
         * Lightning spawned naturally during a thunderstorm.
         */
        WEATHER,
        /**
         * Lightning spawned by a trident with Channeling enchantment.
         */
        TRIDENT,
        /**
         * Lightning spawned by a command.
         */
        COMMAND,
        /**
         * Lightning spawned by a plugin or other custom means.
         */
        CUSTOM
    }

    /**
     * Dimension receiving the proposed bolt.
     */
    protected Dimension dimension;
    /**
     * {@code lightningBolt} references the created entity that listeners can
     * inspect before the dimension requests its addition.
     */
    protected EntityLightningBolt lightningBolt;
    /**
     * Reported source of the strike.
     */
    protected Cause cause;

    /**
     * Creates a lightning proposal retaining its dimension, entity, and
     * reported cause.
     */
    public LightningStrikeEvent(Dimension dimension, EntityLightningBolt lightningBolt, Cause cause) {
        super(dimension.getWorld());
        this.dimension = dimension;
        this.lightningBolt = lightningBolt;
        this.cause = cause;
    }
}
