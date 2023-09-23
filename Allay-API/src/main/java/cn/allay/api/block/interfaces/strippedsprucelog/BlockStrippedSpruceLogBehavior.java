package cn.allay.api.block.interfaces.strippedsprucelog;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockStrippedSpruceLogBehavior extends BlockBehavior {
    BlockType<BlockStrippedSpruceLogBehavior> STRIPPED_SPRUCE_LOG_TYPE = BlockTypeBuilder
            .builder(BlockStrippedSpruceLogBehavior.class)
            .vanillaBlock(VanillaBlockId.STRIPPED_SPRUCE_LOG)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
}
