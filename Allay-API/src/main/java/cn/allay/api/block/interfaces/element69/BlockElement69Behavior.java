package cn.allay.api.block.interfaces.element69;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement69Behavior extends BlockBehavior {
    BlockType<BlockElement69Behavior> ELEMENT_69_TYPE = BlockTypeBuilder
            .builder(BlockElement69Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_69)
            .build();
}
