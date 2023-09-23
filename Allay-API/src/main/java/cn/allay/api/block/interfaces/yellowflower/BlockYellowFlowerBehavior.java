package cn.allay.api.block.interfaces.yellowflower;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockYellowFlowerBehavior extends BlockBehavior {
    BlockType<BlockYellowFlowerBehavior> YELLOW_FLOWER_TYPE = BlockTypeBuilder
            .builder(BlockYellowFlowerBehavior.class)
            .vanillaBlock(VanillaBlockId.YELLOW_FLOWER)
            .build();
}
