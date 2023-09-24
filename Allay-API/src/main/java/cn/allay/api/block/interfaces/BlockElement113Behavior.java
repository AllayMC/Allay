package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement113Behavior extends BlockBehavior {
    BlockType<BlockElement113Behavior> ELEMENT_113_TYPE = BlockTypeBuilder
            .builder(BlockElement113Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_113)
            .build();
}
