package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement103Behavior extends BlockBehavior {
    BlockType<BlockElement103Behavior> ELEMENT_103_TYPE = BlockTypeBuilder
            .builder(BlockElement103Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_103)
            .build();
}
