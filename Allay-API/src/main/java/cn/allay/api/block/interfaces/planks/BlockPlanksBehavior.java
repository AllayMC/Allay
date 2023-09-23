package cn.allay.api.block.interfaces.planks;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockPlanksBehavior extends BlockBehavior {
    BlockType<BlockPlanksBehavior> PLANKS_TYPE = BlockTypeBuilder
            .builder(BlockPlanksBehavior.class)
            .vanillaBlock(VanillaBlockId.PLANKS)
            .setProperties(VanillaBlockPropertyTypes.WOOD_TYPE)
            .build();
}
