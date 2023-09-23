package cn.allay.api.block.interfaces.element4;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement4Behavior extends BlockBehavior {
    BlockType<BlockElement4Behavior> ELEMENT_4_TYPE = BlockTypeBuilder
            .builder(BlockElement4Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_4)
            .build();
}
