package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockRedNetherBrickBehavior extends BlockBehavior {
    BlockType<BlockRedNetherBrickBehavior> RED_NETHER_BRICK_TYPE = BlockTypeBuilder
            .builder(BlockRedNetherBrickBehavior.class)
            .vanillaBlock(VanillaBlockId.RED_NETHER_BRICK)
            .build();
}
