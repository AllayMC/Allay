package org.allaymc.server.block.component.button;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;

import java.time.Duration;

/**
 * {@code BlockWoodenButtonBaseComponentImpl} specializes button behavior with a longer
 * activation interval for wooden buttons. The inherited component handles attachment,
 * player activation, power, and release; this constructor selects a duration of one and
 * a half seconds for scheduled release.
 *
 * @author Dhaiven
 */
public class BlockWoodenButtonBaseComponentImpl extends BlockButtonBaseComponentImpl {
    /**
     * Creates a wooden button component with a one-and-a-half-second activation
     * interval. The inherited player-press path uses that interval for release
     * scheduling; this specialization does not implement arrow-triggered activation.
     */
    public BlockWoodenButtonBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType, Duration.ofMillis(1500));
    }

    // TODO: Activate wooden buttons when an arrow intersects their collision shape.
}
