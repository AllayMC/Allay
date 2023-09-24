package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement71Behavior extends BlockBehavior {
    BlockType<BlockElement71Behavior> ELEMENT_71_TYPE = BlockTypeBuilder
            .builder(BlockElement71Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_71)
            .build();
}
