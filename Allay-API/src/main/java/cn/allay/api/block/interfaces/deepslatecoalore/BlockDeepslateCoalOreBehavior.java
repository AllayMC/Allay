package cn.allay.api.block.interfaces.deepslatecoalore;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockDeepslateCoalOreBehavior extends BlockBehavior {
    BlockType<BlockDeepslateCoalOreBehavior> DEEPSLATE_COAL_ORE_TYPE = BlockTypeBuilder
            .builder(BlockDeepslateCoalOreBehavior.class)
            .vanillaBlock(VanillaBlockId.DEEPSLATE_COAL_ORE)
            .build();
}
