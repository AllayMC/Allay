package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement29Behavior extends BlockBehavior {
    BlockType<BlockElement29Behavior> ELEMENT_29_TYPE = BlockTypeBuilder
            .builder(BlockElement29Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_29)
            .build();
}
