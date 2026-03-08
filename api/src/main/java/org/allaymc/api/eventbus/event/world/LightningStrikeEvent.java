package org.allaymc.api.eventbus.event.world;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityLightningBolt;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.world.Dimension;

/**
 * Called when a lightning bolt is about to strike.
 * <p>
 * This event is fired for both natural lightning strikes during thunderstorms
 * and lightning summoned through other means (e.g., trident with Channeling enchantment).
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.DIMENSION)
public class LightningStrikeEvent extends WorldEvent implements CancellableEvent {

    /**
     * The cause of the lightning strike.
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

    protected Dimension dimension;
    protected EntityLightningBolt lightningBolt;
    protected Cause cause;

    public LightningStrikeEvent(Dimension dimension, EntityLightningBolt lightningBolt, Cause cause) {
        super(dimension.getWorld());
        this.dimension = dimension;
        this.lightningBolt = lightningBolt;
        this.cause = cause;
    }
}
