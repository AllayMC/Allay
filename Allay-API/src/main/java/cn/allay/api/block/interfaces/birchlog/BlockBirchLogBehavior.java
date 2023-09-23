package cn.allay.api.block.interfaces.birchlog;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockBirchLogBehavior extends BlockBehavior {
    BlockType<BlockBirchLogBehavior> BIRCH_LOG_TYPE = BlockTypeBuilder
            .builder(BlockBirchLogBehavior.class)
            .vanillaBlock(VanillaBlockId.BIRCH_LOG)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
}
