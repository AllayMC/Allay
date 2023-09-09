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
public interface BlockBigDripleafBehavior extends BlockBehavior {
    BlockType<BlockBigDripleafBehavior> BIG_DRIPLEAF_TYPE = BlockTypeBuilder
            .builder(BlockBigDripleafBehavior.class)
            .vanillaBlock(VanillaBlockId.BIG_DRIPLEAF)
            .setProperties(VanillaBlockPropertyTypes.BIG_DRIPLEAF_HEAD, VanillaBlockPropertyTypes.BIG_DRIPLEAF_TILT, VanillaBlockPropertyTypes.DIRECTION)
            .build();
}
