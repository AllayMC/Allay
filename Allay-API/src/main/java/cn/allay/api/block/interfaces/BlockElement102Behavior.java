package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement102Behavior extends BlockBehavior {
    BlockType<BlockElement102Behavior> ELEMENT_102_TYPE = BlockTypeBuilder
            .builder(BlockElement102Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_102)
            .build();
}
