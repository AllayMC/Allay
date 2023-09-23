package cn.allay.api.block.interfaces.structureblock;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockStructureBlockBehavior extends BlockBehavior {
    BlockType<BlockStructureBlockBehavior> STRUCTURE_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockStructureBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.STRUCTURE_BLOCK)
            .setProperties(VanillaBlockPropertyTypes.STRUCTURE_BLOCK_TYPE)
            .build();
}
