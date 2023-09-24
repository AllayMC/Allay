package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement117Behavior extends BlockBehavior {
    BlockType<BlockElement117Behavior> ELEMENT_117_TYPE = BlockTypeBuilder
            .builder(BlockElement117Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_117)
            .build();
}
