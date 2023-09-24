package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockNetherBrickBehavior extends BlockBehavior {
    BlockType<BlockNetherBrickBehavior> NETHER_BRICK_TYPE = BlockTypeBuilder
            .builder(BlockNetherBrickBehavior.class)
            .vanillaBlock(VanillaBlockId.NETHER_BRICK)
            .build();
}
