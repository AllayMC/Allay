package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement84Behavior extends BlockBehavior {
    BlockType<BlockElement84Behavior> ELEMENT_84_TYPE = BlockTypeBuilder
            .builder(BlockElement84Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_84)
            .build();
}
