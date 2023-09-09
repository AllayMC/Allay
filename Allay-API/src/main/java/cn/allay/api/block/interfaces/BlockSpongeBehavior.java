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
public interface BlockSpongeBehavior extends BlockBehavior {
    BlockType<BlockSpongeBehavior> SPONGE_TYPE = BlockTypeBuilder
            .builder(BlockSpongeBehavior.class)
            .vanillaBlock(VanillaBlockId.SPONGE)
            .setProperties(VanillaBlockPropertyTypes.SPONGE_TYPE)
            .build();
}
