package cn.allay.api.block.interfaces.tallgrass;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockTallgrassBehavior extends BlockBehavior {
    BlockType<BlockTallgrassBehavior> TALLGRASS_TYPE = BlockTypeBuilder
            .builder(BlockTallgrassBehavior.class)
            .vanillaBlock(VanillaBlockId.TALLGRASS)
            .setProperties(VanillaBlockPropertyTypes.TALL_GRASS_TYPE)
            .build();
}
