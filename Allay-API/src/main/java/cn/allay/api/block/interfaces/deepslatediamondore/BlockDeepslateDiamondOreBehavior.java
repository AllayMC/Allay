package cn.allay.api.block.interfaces.deepslatediamondore;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockDeepslateDiamondOreBehavior extends BlockBehavior {
    BlockType<BlockDeepslateDiamondOreBehavior> DEEPSLATE_DIAMOND_ORE_TYPE = BlockTypeBuilder
            .builder(BlockDeepslateDiamondOreBehavior.class)
            .vanillaBlock(VanillaBlockId.DEEPSLATE_DIAMOND_ORE)
            .build();
}
