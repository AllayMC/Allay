package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockCobbledDeepslateBehavior extends BlockBehavior {
    BlockType<BlockCobbledDeepslateBehavior> COBBLED_DEEPSLATE_TYPE = BlockTypeBuilder
            .builder(BlockCobbledDeepslateBehavior.class)
            .vanillaBlock(VanillaBlockId.COBBLED_DEEPSLATE)
            .build();
}
