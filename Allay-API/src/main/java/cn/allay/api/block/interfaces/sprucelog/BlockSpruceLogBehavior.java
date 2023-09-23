package cn.allay.api.block.interfaces.sprucelog;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockSpruceLogBehavior extends BlockBehavior {
    BlockType<BlockSpruceLogBehavior> SPRUCE_LOG_TYPE = BlockTypeBuilder
            .builder(BlockSpruceLogBehavior.class)
            .vanillaBlock(VanillaBlockId.SPRUCE_LOG)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
}
