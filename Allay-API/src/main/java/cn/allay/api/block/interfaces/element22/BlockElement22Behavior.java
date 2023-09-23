package cn.allay.api.block.interfaces.element22;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement22Behavior extends BlockBehavior {
    BlockType<BlockElement22Behavior> ELEMENT_22_TYPE = BlockTypeBuilder
            .builder(BlockElement22Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_22)
            .build();
}
