package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockSlimeBehavior extends BlockBehavior {
    BlockType<BlockSlimeBehavior> SLIME_TYPE = BlockTypeBuilder
            .builder(BlockSlimeBehavior.class)
            .vanillaBlock(VanillaBlockId.SLIME)
            .build();
}
