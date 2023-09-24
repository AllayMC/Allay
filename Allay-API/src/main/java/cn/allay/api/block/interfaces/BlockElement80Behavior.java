package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement80Behavior extends BlockBehavior {
    BlockType<BlockElement80Behavior> ELEMENT_80_TYPE = BlockTypeBuilder
            .builder(BlockElement80Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_80)
            .build();
}
