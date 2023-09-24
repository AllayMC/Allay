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
public interface BlockCarrotsBehavior extends BlockBehavior {
    BlockType<BlockCarrotsBehavior> CARROTS_TYPE = BlockTypeBuilder
            .builder(BlockCarrotsBehavior.class)
            .vanillaBlock(VanillaBlockId.CARROTS)
            .setProperties(VanillaBlockPropertyTypes.GROWTH)
            .build();
}
