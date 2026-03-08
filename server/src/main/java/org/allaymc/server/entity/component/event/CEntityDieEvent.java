package org.allaymc.server.entity.component.event;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.eventbus.event.Event;
import org.joml.Vector3d;
import org.joml.Vector3dc;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

/**
 * @author daoge_cmd
 */
public class CEntityDieEvent extends Event {

    @Getter @Setter
    private Vector3dc dropPosition;
    @Getter @Setter
    private Supplier<Vector3dc> dropMotionFactory = () -> {
        var rand = ThreadLocalRandom.current();
        return new Vector3d(rand.nextDouble() * 0.2 - 0.1, 0.2, rand.nextDouble() * 0.2 - 0.1);
    };
}
