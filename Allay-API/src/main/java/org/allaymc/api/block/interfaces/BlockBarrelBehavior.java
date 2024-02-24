package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.common.BlockEntityHolderComponent;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.blockentity.interfaces.BlockEntityBarrel;
import org.allaymc.api.blockentity.type.BlockEntityTypes;
import org.allaymc.api.data.VanillaBlockId;

import static org.allaymc.api.block.component.BlockComponentImplFactory.getFactory;
import static org.allaymc.api.data.VanillaBlockPropertyTypes.FACING_DIRECTION;
import static org.allaymc.api.data.VanillaBlockPropertyTypes.OPEN_BIT;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockBarrelBehavior extends
        BlockBehavior, BlockEntityHolderComponent<BlockEntityBarrel> {
    BlockType<BlockBarrelBehavior> BARREL_TYPE = BlockTypeBuilder
            .builder(BlockBarrelBehavior.class)
            .vanillaBlock(VanillaBlockId.BARREL)
            .setProperties(FACING_DIRECTION, OPEN_BIT)
            .build();
}
