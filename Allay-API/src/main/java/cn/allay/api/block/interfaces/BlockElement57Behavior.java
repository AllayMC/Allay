package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement57Behavior extends BlockBehavior {
    BlockType<BlockElement57Behavior> ELEMENT_57_TYPE = BlockTypeBuilder
            .builder(BlockElement57Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_57)
            .build();
}
