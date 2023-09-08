package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement51Behavior extends BlockBehavior {
    BlockType<BlockElement51Behavior> ELEMENT_51_TYPE = BlockTypeBuilder
            .builder(BlockElement51Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_51)
            .build();
}
