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
public interface BlockSoulFireBehavior extends BlockBehavior {
    BlockType<BlockSoulFireBehavior> SOUL_FIRE_TYPE = BlockTypeBuilder
            .builder(BlockSoulFireBehavior.class)
            .vanillaBlock(VanillaBlockId.SOUL_FIRE)
            .setProperties(VanillaBlockPropertyTypes.AGE_16)
            .build();
}
