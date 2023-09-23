package cn.allay.api.block.interfaces.purpurblock;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockPurpurBlockBehavior extends BlockBehavior {
    BlockType<BlockPurpurBlockBehavior> PURPUR_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockPurpurBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.PURPUR_BLOCK)
            .setProperties(VanillaBlockPropertyTypes.CHISEL_TYPE, VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
}
