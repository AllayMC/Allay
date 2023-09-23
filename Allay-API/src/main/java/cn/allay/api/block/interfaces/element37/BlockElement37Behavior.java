package cn.allay.api.block.interfaces.element37;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement37Behavior extends BlockBehavior {
    BlockType<BlockElement37Behavior> ELEMENT_37_TYPE = BlockTypeBuilder
            .builder(BlockElement37Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_37)
            .build();
}
