package cn.allay.api.block.interfaces.campfire;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockCampfireBehavior extends BlockBehavior {
    BlockType<BlockCampfireBehavior> CAMPFIRE_TYPE = BlockTypeBuilder
            .builder(BlockCampfireBehavior.class)
            .vanillaBlock(VanillaBlockId.CAMPFIRE)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.EXTINGUISHED)
            .build();
}
