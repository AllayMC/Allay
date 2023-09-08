package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement61Behavior extends BlockBehavior {
    BlockType<BlockElement61Behavior> ELEMENT_61_TYPE = BlockTypeBuilder
            .builder(BlockElement61Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_61)
            .build();
}
