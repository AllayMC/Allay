package cn.allay.api.block.interfaces.blastfurnace;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockBlastFurnaceBehavior extends BlockBehavior {
    BlockType<BlockBlastFurnaceBehavior> BLAST_FURNACE_TYPE = BlockTypeBuilder
            .builder(BlockBlastFurnaceBehavior.class)
            .vanillaBlock(VanillaBlockId.BLAST_FURNACE)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
}
