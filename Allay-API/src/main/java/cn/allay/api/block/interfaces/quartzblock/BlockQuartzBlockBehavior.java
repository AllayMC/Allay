package cn.allay.api.block.interfaces.quartzblock;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockQuartzBlockBehavior extends BlockBehavior {
    BlockType<BlockQuartzBlockBehavior> QUARTZ_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockQuartzBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.QUARTZ_BLOCK)
            .setProperties(VanillaBlockPropertyTypes.CHISEL_TYPE, VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
}
