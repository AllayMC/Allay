package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement36Behavior extends BlockBehavior {
    BlockType<BlockElement36Behavior> ELEMENT_36_TYPE = BlockTypeBuilder
            .builder(BlockElement36Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_36)
            .build();
}
