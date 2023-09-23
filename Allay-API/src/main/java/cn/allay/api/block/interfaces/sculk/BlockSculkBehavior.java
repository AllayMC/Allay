package cn.allay.api.block.interfaces.sculk;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockSculkBehavior extends BlockBehavior {
    BlockType<BlockSculkBehavior> SCULK_TYPE = BlockTypeBuilder
            .builder(BlockSculkBehavior.class)
            .vanillaBlock(VanillaBlockId.SCULK)
            .build();
}
