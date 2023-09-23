package cn.allay.api.block.interfaces.element20;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement20Behavior extends BlockBehavior {
    BlockType<BlockElement20Behavior> ELEMENT_20_TYPE = BlockTypeBuilder
            .builder(BlockElement20Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_20)
            .build();
}
