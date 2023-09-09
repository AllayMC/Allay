package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement55Behavior extends BlockBehavior {
    BlockType<BlockElement55Behavior> ELEMENT_55_TYPE = BlockTypeBuilder
            .builder(BlockElement55Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_55)
            .build();
}
