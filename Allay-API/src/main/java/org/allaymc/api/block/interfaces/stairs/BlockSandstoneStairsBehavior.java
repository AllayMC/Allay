package org.allaymc.api.block.interfaces.stairs;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.attribute.BlockAttributeComponentImpl;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;
import org.allaymc.api.math.voxelshape.CommonShapes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockSandstoneStairsBehavior extends BlockBehavior {
    BlockType<BlockSandstoneStairsBehavior> SANDSTONE_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockSandstoneStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.SANDSTONE_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION).addComponent(BlockAttributeComponentImpl.ofRedefinedAABB(CommonShapes::buildStairShape))
            .setBlockBaseComponentSupplier(BlockStairsBaseComponentImpl::new)
            .build();
}