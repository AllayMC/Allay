package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement109Behavior extends BlockBehavior {
    BlockType<BlockElement109Behavior> ELEMENT_109_TYPE = BlockTypeBuilder
            .builder(BlockElement109Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_109)
            .build();
}
