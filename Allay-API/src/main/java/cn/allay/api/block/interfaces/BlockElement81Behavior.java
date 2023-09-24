package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement81Behavior extends BlockBehavior {
    BlockType<BlockElement81Behavior> ELEMENT_81_TYPE = BlockTypeBuilder
            .builder(BlockElement81Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_81)
            .build();
}
