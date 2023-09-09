package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement45Behavior extends BlockBehavior {
    BlockType<BlockElement45Behavior> ELEMENT_45_TYPE = BlockTypeBuilder
            .builder(BlockElement45Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_45)
            .build();
}
