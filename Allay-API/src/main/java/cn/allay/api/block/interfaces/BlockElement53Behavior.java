package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement53Behavior extends BlockBehavior {
    BlockType<BlockElement53Behavior> ELEMENT_53_TYPE = BlockTypeBuilder
            .builder(BlockElement53Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_53)
            .build();
}
