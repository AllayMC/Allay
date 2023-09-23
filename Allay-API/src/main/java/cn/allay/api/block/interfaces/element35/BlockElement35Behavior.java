package cn.allay.api.block.interfaces.element35;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement35Behavior extends BlockBehavior {
    BlockType<BlockElement35Behavior> ELEMENT_35_TYPE = BlockTypeBuilder
            .builder(BlockElement35Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_35)
            .build();
}
