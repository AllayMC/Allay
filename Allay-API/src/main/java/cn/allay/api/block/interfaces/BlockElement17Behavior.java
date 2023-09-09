package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement17Behavior extends BlockBehavior {
    BlockType<BlockElement17Behavior> ELEMENT_17_TYPE = BlockTypeBuilder
            .builder(BlockElement17Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_17)
            .build();
}
