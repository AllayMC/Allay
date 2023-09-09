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
public interface BlockSeaPickleBehavior extends BlockBehavior {
    BlockType<BlockSeaPickleBehavior> SEA_PICKLE_TYPE = BlockTypeBuilder
            .builder(BlockSeaPickleBehavior.class)
            .vanillaBlock(VanillaBlockId.SEA_PICKLE)
            .setProperties(VanillaBlockPropertyTypes.CLUSTER_COUNT, VanillaBlockPropertyTypes.DEAD_BIT)
            .build();
}
