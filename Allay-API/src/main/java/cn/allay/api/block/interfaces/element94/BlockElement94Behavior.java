package cn.allay.api.block.interfaces.element94;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement94Behavior extends BlockBehavior {
    BlockType<BlockElement94Behavior> ELEMENT_94_TYPE = BlockTypeBuilder
            .builder(BlockElement94Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_94)
            .build();
}
