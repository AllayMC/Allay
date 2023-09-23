package cn.allay.api.block.interfaces.element96;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement96Behavior extends BlockBehavior {
    BlockType<BlockElement96Behavior> ELEMENT_96_TYPE = BlockTypeBuilder
            .builder(BlockElement96Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_96)
            .build();
}
