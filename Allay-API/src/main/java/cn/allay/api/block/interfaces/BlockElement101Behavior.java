package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement101Behavior extends BlockBehavior {
    BlockType<BlockElement101Behavior> ELEMENT_101_TYPE = BlockTypeBuilder
            .builder(BlockElement101Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_101)
            .build();
}
