package org.allaymc.server.entity.component.event;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.eventbus.event.Event;
import org.joml.Vector3d;
import org.joml.Vector3dc;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

/**
 * {@code CEntityDieEvent} carries the drop position and per-item motion
 * supplier used during internal death processing. The living component
 * supplies the initial entity position before dispatch, then uses
 * listener-adjusted values when producing drops; experience orbs still use the
 * entity's location separately.
 *
 * @author daoge_cmd
 */
public class CEntityDieEvent extends Event {

    /**
     * {@code dropPosition} is the retained position used for item drops after
     * death handlers run. The living component initializes it before dispatch,
     * and handlers may replace the reference.
     */
    @Getter @Setter
    private Vector3dc dropPosition;
    /**
     * {@code dropMotionFactory} provides a motion vector for each item drop.
     * The default creates fresh vectors with upward velocity {@code 0.2} and
     * horizontal components in {@code [-0.1, 0.1)} blocks per tick.
     */
    @Getter @Setter
    private Supplier<Vector3dc> dropMotionFactory = () -> {
        var rand = ThreadLocalRandom.current();
        // Give item drops a small horizontal spread while keeping the initial
        // upward speed fixed at one fifth of a block per tick.
        return new Vector3d(rand.nextDouble() * 0.2 - 0.1, 0.2, rand.nextDouble() * 0.2 - 0.1);
    };
}
