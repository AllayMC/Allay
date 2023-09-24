package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement10Behavior extends BlockBehavior {
    BlockType<BlockElement10Behavior> ELEMENT_10_TYPE = BlockTypeBuilder
            .builder(BlockElement10Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_10)
            .build();
}
