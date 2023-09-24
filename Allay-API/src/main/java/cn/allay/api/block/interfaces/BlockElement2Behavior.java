package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement2Behavior extends BlockBehavior {
    BlockType<BlockElement2Behavior> ELEMENT_2_TYPE = BlockTypeBuilder
            .builder(BlockElement2Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_2)
            .build();
}
