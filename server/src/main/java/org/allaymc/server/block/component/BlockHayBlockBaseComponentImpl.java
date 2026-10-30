package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;

/**
 * {@code BlockHayBlockBaseComponentImpl} reduces fall damage for entities landing on
 * hay blocks. The reduction factor is read by living-entity damage calculation, which
 * applies the remaining fraction to raw damage; other block lifecycle and drop
 * behavior comes from the shared base.
 *
 * @author daoge_cmd
 */
public class BlockHayBlockBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * Creates the hay block block behavior for the supplied block type.
     */
    public BlockHayBlockBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Returns an eighty-percent reduction factor for landing fall damage.
     */
    @Override
    public float getFallDamageReductionFactor() {
        return 0.8f;
    }
}
