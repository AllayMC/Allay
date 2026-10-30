package org.allaymc.api.eventbus.event.world;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.world.World;

/**
 * {@code WorldUnloadEvent} represents a loaded world proposed for removal from
 * the pool. The pool dispatches it before requesting shutdown and removing the
 * entry; cancellation preserves the entry, while an accepted notification does
 * not wait for world cleanup to finish.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.SERVER)
public class WorldUnloadEvent extends WorldEvent implements CancellableEvent {
    /**
     * Creates a pool removal proposal referring to the supplied world.
     */
    public WorldUnloadEvent(World world) {
        super(world);
    }
}
