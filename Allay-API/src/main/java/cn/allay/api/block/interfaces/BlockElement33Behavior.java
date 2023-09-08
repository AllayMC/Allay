package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement33Behavior extends BlockBehavior {
    BlockType<BlockElement33Behavior> ELEMENT_33_TYPE = BlockTypeBuilder
            .builder(BlockElement33Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_33)
            .build();
}
