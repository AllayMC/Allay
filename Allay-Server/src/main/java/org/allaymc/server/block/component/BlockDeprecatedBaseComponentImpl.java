package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.RequireBlockProperty;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.server.block.component.facing.BlockPillarAxisComponentImpl;

@RequireBlockProperty(type = BlockPropertyType.Type.INT, name = "deprecated")
public class BlockDeprecatedBaseComponentImpl extends BlockPillarAxisComponentImpl {
    public BlockDeprecatedBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }
}
