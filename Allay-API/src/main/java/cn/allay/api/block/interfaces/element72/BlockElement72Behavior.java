package cn.allay.api.block.interfaces.element72;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement72Behavior extends BlockBehavior {
    BlockType<BlockElement72Behavior> ELEMENT_72_TYPE = BlockTypeBuilder
            .builder(BlockElement72Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_72)
            .build();
}
