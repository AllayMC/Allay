package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement8Behavior extends BlockBehavior {
    BlockType<BlockElement8Behavior> ELEMENT_8_TYPE = BlockTypeBuilder
            .builder(BlockElement8Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_8)
            .build();
}
