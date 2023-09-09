package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockJigsawBehavior extends BlockBehavior {
    BlockType<BlockJigsawBehavior> JIGSAW_TYPE = BlockTypeBuilder
            .builder(BlockJigsawBehavior.class)
            .vanillaBlock(VanillaBlockId.JIGSAW)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.ROTATION)
            .build();
}
