package cn.allay.api.block.interfaces.element47;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement47Behavior extends BlockBehavior {
    BlockType<BlockElement47Behavior> ELEMENT_47_TYPE = BlockTypeBuilder
            .builder(BlockElement47Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_47)
            .build();
}
