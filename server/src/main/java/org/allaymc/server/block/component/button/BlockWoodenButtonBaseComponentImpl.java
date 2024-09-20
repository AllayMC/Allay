package org.allaymc.server.block.component.button;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;

import java.time.Duration;

/**
 * @author Dhaiven
 */
public class BlockWoodenButtonBaseComponentImpl extends BlockButtonBaseComponentImpl {
    public BlockWoodenButtonBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType, Duration.ofMillis(1500)); // 1.5 second
    }

    //TODO: arrow can active wooden button
}
