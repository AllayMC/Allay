package org.allaymc.server.block.component.sign;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.RequireBlockProperty;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.server.block.component.common.BlockBaseComponentImpl;

/**
 * Allay Project 2024/8/17
 *
 * @author daoge_cmd
 */
@RequireBlockProperty(type = BlockPropertyType.Type.BOOLEAN, name = "attached_bit")
@RequireBlockProperty(type = BlockPropertyType.Type.INT, name = "facing_direction")
@RequireBlockProperty(type = BlockPropertyType.Type.INT, name = "ground_sign_direction")
@RequireBlockProperty(type = BlockPropertyType.Type.BOOLEAN, name = "hanging")
public class BlockHangingSignBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockHangingSignBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }
}
