package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement1Behavior extends BlockBehavior {
    BlockType<BlockElement1Behavior> ELEMENT_1_TYPE = BlockTypeBuilder
            .builder(BlockElement1Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_1)
            .build();
}
