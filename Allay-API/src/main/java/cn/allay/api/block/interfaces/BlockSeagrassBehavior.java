package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockSeagrassBehavior extends BlockBehavior {
    BlockType<BlockSeagrassBehavior> SEAGRASS_TYPE = BlockTypeBuilder
            .builder(BlockSeagrassBehavior.class)
            .vanillaBlock(VanillaBlockId.SEAGRASS)
            .setProperties(VanillaBlockPropertyTypes.SEA_GRASS_TYPE)
            .build();
}
