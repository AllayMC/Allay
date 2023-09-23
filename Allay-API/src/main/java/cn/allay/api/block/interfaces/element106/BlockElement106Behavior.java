package cn.allay.api.block.interfaces.element106;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement106Behavior extends BlockBehavior {
    BlockType<BlockElement106Behavior> ELEMENT_106_TYPE = BlockTypeBuilder
            .builder(BlockElement106Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_106)
            .build();
}
