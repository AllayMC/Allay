package org.allaymc.server.block.component.redstone.button;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.RequireBlockProperty;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockType;

import java.time.Duration;

@RequireBlockProperty(type = BlockPropertyType.Type.BOOLEAN, name = "button_pressed_bit")
@RequireBlockProperty(type = BlockPropertyType.Type.INT, name = "facing_direction")
public class BlockWoodenButtonComponentImpl extends BlockButtonBaseComponentImpl {
    public BlockWoodenButtonComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    public Duration getActivationTime() {
        return Duration.ofMillis(1500); // 1.5 second
    }

    //TODO: arrow can active wooden button
}
