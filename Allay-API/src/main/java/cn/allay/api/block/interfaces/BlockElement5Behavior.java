package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement5Behavior extends BlockBehavior {
    BlockType<BlockElement5Behavior> ELEMENT_5_TYPE = BlockTypeBuilder
            .builder(BlockElement5Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_5)
            .build();
}
