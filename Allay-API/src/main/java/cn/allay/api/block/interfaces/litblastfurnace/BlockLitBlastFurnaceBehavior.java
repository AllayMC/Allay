package cn.allay.api.block.interfaces.litblastfurnace;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockLitBlastFurnaceBehavior extends BlockBehavior {
    BlockType<BlockLitBlastFurnaceBehavior> LIT_BLAST_FURNACE_TYPE = BlockTypeBuilder
            .builder(BlockLitBlastFurnaceBehavior.class)
            .vanillaBlock(VanillaBlockId.LIT_BLAST_FURNACE)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
}
