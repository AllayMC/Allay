package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;
import org.allaymc.api.math.voxelshape.VoxelShape;

import static org.allaymc.api.block.component.BlockComponentImplFactory.getFactory;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockCauldronBehavior extends BlockBehavior {
    BlockType<BlockCauldronBehavior> CAULDRON_TYPE = BlockTypeBuilder
            .builder(BlockCauldronBehavior.class)
            .vanillaBlock(VanillaBlockId.CAULDRON)
            .setProperties(VanillaBlockPropertyTypes.CAULDRON_LIQUID, VanillaBlockPropertyTypes.FILL_LEVEL)
            .addComponent(getFactory().createRedefinedAABBBlockAttributeComponent(
                    blockState ->
                            VoxelShape
                                    .builder()
                                    .solid(0, 0, 0, 1, 1, 1)
                                    .vacancy(0.125f, 0.3125f, 0.125f, 0.875f, 1, 0.875f)
                                    .build()
            ))
            .build();
}
