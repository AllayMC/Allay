package cn.allay.api.block.interfaces.element50;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement50Behavior extends BlockBehavior {
    BlockType<BlockElement50Behavior> ELEMENT_50_TYPE = BlockTypeBuilder
            .builder(BlockElement50Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_50)
            .build();
}
