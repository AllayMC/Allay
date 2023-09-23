package cn.allay.api.block.interfaces.element91;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement91Behavior extends BlockBehavior {
    BlockType<BlockElement91Behavior> ELEMENT_91_TYPE = BlockTypeBuilder
            .builder(BlockElement91Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_91)
            .build();
}
