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
public interface BlockRedFlowerBehavior extends BlockBehavior {
    BlockType<BlockRedFlowerBehavior> RED_FLOWER_TYPE = BlockTypeBuilder
            .builder(BlockRedFlowerBehavior.class)
            .vanillaBlock(VanillaBlockId.RED_FLOWER)
            .setProperties(VanillaBlockPropertyTypes.FLOWER_TYPE)
            .build();
}
