package org.allaymc.server.block.component.redstone.button;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.RequireBlockProperty;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockType;

import java.time.Duration;

@RequireBlockProperty(type = BlockPropertyType.Type.BOOLEAN, name = "button_pressed_bit")
@RequireBlockProperty(type = BlockPropertyType.Type.INT, name = "facing_direction")
public class BlockStoneButtonComponentImpl extends BlockButtonBaseComponentImpl {
    public BlockStoneButtonComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    public Duration getActivationTime() {
        return Duration.ofSeconds(1);
    }
}
