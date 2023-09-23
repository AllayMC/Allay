package cn.allay.api.block.interfaces.magma;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockMagmaBehavior extends BlockBehavior {
    BlockType<BlockMagmaBehavior> MAGMA_TYPE = BlockTypeBuilder
            .builder(BlockMagmaBehavior.class)
            .vanillaBlock(VanillaBlockId.MAGMA)
            .build();
}
