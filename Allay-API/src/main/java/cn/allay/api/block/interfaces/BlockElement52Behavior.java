package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement52Behavior extends BlockBehavior {
    BlockType<BlockElement52Behavior> ELEMENT_52_TYPE = BlockTypeBuilder
            .builder(BlockElement52Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_52)
            .build();
}
