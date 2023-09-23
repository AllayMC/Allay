package cn.allay.api.block.interfaces.element114;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement114Behavior extends BlockBehavior {
    BlockType<BlockElement114Behavior> ELEMENT_114_TYPE = BlockTypeBuilder
            .builder(BlockElement114Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_114)
            .build();
}
