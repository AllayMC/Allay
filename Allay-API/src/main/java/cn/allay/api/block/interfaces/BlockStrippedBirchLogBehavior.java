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
public interface BlockStrippedBirchLogBehavior extends BlockBehavior {
    BlockType<BlockStrippedBirchLogBehavior> STRIPPED_BIRCH_LOG_TYPE = BlockTypeBuilder
            .builder(BlockStrippedBirchLogBehavior.class)
            .vanillaBlock(VanillaBlockId.STRIPPED_BIRCH_LOG)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
}
