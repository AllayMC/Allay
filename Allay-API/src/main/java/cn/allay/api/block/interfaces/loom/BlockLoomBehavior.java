package cn.allay.api.block.interfaces.loom;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockLoomBehavior extends BlockBehavior {
    BlockType<BlockLoomBehavior> LOOM_TYPE = BlockTypeBuilder
            .builder(BlockLoomBehavior.class)
            .vanillaBlock(VanillaBlockId.LOOM)
            .setProperties(VanillaBlockPropertyTypes.DIRECTION)
            .build();
}
