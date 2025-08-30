package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.world.Explosion;

/**
 * Called when a block (e.g. bed) explode.
 *
 * @author daoge_cmd
 */
@Getter
@Setter
public class BlockExplodeEvent extends BlockEvent implements CancellableEvent {
    /**
     * The explosion.
     */
    protected Explosion explosion;

    public BlockExplodeEvent(Block block, Explosion explosion) {
        super(block);
        this.explosion = explosion;
    }
}
