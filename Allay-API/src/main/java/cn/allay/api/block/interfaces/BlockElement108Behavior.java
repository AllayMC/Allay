package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement108Behavior extends BlockBehavior {
    BlockType<BlockElement108Behavior> ELEMENT_108_TYPE = BlockTypeBuilder
            .builder(BlockElement108Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_108)
            .build();
}
