package cn.allay.api.block.interfaces.deepslategoldore;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockDeepslateGoldOreBehavior extends BlockBehavior {
    BlockType<BlockDeepslateGoldOreBehavior> DEEPSLATE_GOLD_ORE_TYPE = BlockTypeBuilder
            .builder(BlockDeepslateGoldOreBehavior.class)
            .vanillaBlock(VanillaBlockId.DEEPSLATE_GOLD_ORE)
            .build();
}
