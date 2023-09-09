package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement6Behavior extends BlockBehavior {
    BlockType<BlockElement6Behavior> ELEMENT_6_TYPE = BlockTypeBuilder
            .builder(BlockElement6Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_6)
            .build();
}
