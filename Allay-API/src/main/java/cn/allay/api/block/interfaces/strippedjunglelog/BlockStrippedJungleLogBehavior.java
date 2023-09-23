package cn.allay.api.block.interfaces.strippedjunglelog;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockStrippedJungleLogBehavior extends BlockBehavior {
    BlockType<BlockStrippedJungleLogBehavior> STRIPPED_JUNGLE_LOG_TYPE = BlockTypeBuilder
            .builder(BlockStrippedJungleLogBehavior.class)
            .vanillaBlock(VanillaBlockId.STRIPPED_JUNGLE_LOG)
            .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
            .build();
}
