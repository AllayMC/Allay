package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement13Behavior extends BlockBehavior {
    BlockType<BlockElement13Behavior> ELEMENT_13_TYPE = BlockTypeBuilder
            .builder(BlockElement13Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_13)
            .build();
}
