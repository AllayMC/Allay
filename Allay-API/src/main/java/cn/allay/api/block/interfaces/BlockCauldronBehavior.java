package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.component.attribute.BlockAttributeComponentImpl;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;
import cn.allay.api.math.voxelshape.CommonShapes;
import cn.allay.api.math.voxelshape.VoxelShape;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockCauldronBehavior extends BlockBehavior {
    BlockType<BlockCauldronBehavior> CAULDRON_TYPE = BlockTypeBuilder
            .builder(BlockCauldronBehavior.class)
            .vanillaBlock(VanillaBlockId.CAULDRON)
            .setProperties(VanillaBlockPropertyTypes.CAULDRON_LIQUID, VanillaBlockPropertyTypes.FILL_LEVEL)
            .addComponent(BlockAttributeComponentImpl.ofRedefinedAABB(
                    blockState ->
                            VoxelShape
                                    .builder()
                                    .solid(0, 0, 0, 1, 1, 1)
                                    .vacancy(0.125f, 0.3125f, 0.125f, 0.875f, 1, 0.875f)
                                    .build()
            ))
            .build();
}
