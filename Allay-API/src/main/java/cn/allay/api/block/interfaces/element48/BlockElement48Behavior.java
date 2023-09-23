package cn.allay.api.block.interfaces.element48;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement48Behavior extends BlockBehavior {
    BlockType<BlockElement48Behavior> ELEMENT_48_TYPE = BlockTypeBuilder
            .builder(BlockElement48Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_48)
            .build();
}
