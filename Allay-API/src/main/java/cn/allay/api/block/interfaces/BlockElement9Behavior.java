package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement9Behavior extends BlockBehavior {
    BlockType<BlockElement9Behavior> ELEMENT_9_TYPE = BlockTypeBuilder
            .builder(BlockElement9Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_9)
            .build();
}
