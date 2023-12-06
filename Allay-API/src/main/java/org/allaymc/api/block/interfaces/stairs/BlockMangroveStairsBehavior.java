package org.allaymc.api.block.interfaces.stairs;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;
import org.allaymc.api.math.voxelshape.CommonShapes;

import static org.allaymc.api.block.component.BlockComponentImplFactory.getFactory;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockMangroveStairsBehavior extends BlockBehavior {
    BlockType<BlockMangroveStairsBehavior> MANGROVE_STAIRS_TYPE = BlockTypeBuilder
            .builder(BlockMangroveStairsBehavior.class)
            .vanillaBlock(VanillaBlockId.MANGROVE_STAIRS)
            .setProperties(VanillaBlockPropertyTypes.UPSIDE_DOWN_BIT, VanillaBlockPropertyTypes.WEIRDO_DIRECTION)
            .addComponent(getFactory().createRedefinedAABBBlockAttributeComponent(CommonShapes::buildStairShape))

            .setBlockBaseComponentSupplier(getFactory()::createBlockStairBaseComponent)
            .build();
}
