package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement93Behavior extends BlockBehavior {
    BlockType<BlockElement93Behavior> ELEMENT_93_TYPE = BlockTypeBuilder
            .builder(BlockElement93Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_93)
            .build();
}
