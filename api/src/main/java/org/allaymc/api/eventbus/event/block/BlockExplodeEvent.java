package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.world.explosion.Explosion;

/**
 * {@code BlockExplodeEvent} represents an explosion proposed by a block such as a bed
 * or respawn anchor. Cancellation prevents that caller from detonating it; current
 * callers retain their original explosion reference, so listeners change its
 * properties through the existing object.
 *
 * @author daoge_cmd
 */
@Getter
@Setter
@CallerThread(ThreadType.DIMENSION)
public class BlockExplodeEvent extends BlockEvent implements CancellableEvent {
    /**
     * {@code explosion} refers to the proposed blast. Current bed and respawn-anchor
     * callers retain the original object when listeners replace this event property.
     */
    protected Explosion explosion;

    /**
     * Creates a block explosion event retaining the proposed explosion object.
     */
    public BlockExplodeEvent(Block block, Explosion explosion) {
        super(block);
        this.explosion = explosion;
    }
}
