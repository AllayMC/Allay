package cn.allay.api.block.interfaces.goldore;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockGoldOreBehavior extends BlockBehavior {
    BlockType<BlockGoldOreBehavior> GOLD_ORE_TYPE = BlockTypeBuilder
            .builder(BlockGoldOreBehavior.class)
            .vanillaBlock(VanillaBlockId.GOLD_ORE)
            .build();
}
