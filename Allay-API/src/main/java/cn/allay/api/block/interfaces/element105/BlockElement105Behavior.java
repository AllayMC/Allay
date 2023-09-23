package cn.allay.api.block.interfaces.element105;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement105Behavior extends BlockBehavior {
    BlockType<BlockElement105Behavior> ELEMENT_105_TYPE = BlockTypeBuilder
            .builder(BlockElement105Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_105)
            .build();
}
