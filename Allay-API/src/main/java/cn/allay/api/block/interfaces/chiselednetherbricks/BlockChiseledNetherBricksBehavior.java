package cn.allay.api.block.interfaces.chiselednetherbricks;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockChiseledNetherBricksBehavior extends BlockBehavior {
    BlockType<BlockChiseledNetherBricksBehavior> CHISELED_NETHER_BRICKS_TYPE = BlockTypeBuilder
            .builder(BlockChiseledNetherBricksBehavior.class)
            .vanillaBlock(VanillaBlockId.CHISELED_NETHER_BRICKS)
            .build();
}
