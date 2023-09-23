package cn.allay.api.block.interfaces.netherbrickfence;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockNetherBrickFenceBehavior extends BlockBehavior {
    BlockType<BlockNetherBrickFenceBehavior> NETHER_BRICK_FENCE_TYPE = BlockTypeBuilder
            .builder(BlockNetherBrickFenceBehavior.class)
            .vanillaBlock(VanillaBlockId.NETHER_BRICK_FENCE)
            .build();
}
