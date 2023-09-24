package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement15Behavior extends BlockBehavior {
    BlockType<BlockElement15Behavior> ELEMENT_15_TYPE = BlockTypeBuilder
            .builder(BlockElement15Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_15)
            .build();
}
