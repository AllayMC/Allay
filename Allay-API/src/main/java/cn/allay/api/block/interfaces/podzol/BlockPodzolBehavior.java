package cn.allay.api.block.interfaces.podzol;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockPodzolBehavior extends BlockBehavior {
    BlockType<BlockPodzolBehavior> PODZOL_TYPE = BlockTypeBuilder
            .builder(BlockPodzolBehavior.class)
            .vanillaBlock(VanillaBlockId.PODZOL)
            .build();
}
