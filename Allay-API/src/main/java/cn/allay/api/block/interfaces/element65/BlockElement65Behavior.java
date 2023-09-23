package cn.allay.api.block.interfaces.element65;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement65Behavior extends BlockBehavior {
    BlockType<BlockElement65Behavior> ELEMENT_65_TYPE = BlockTypeBuilder
            .builder(BlockElement65Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_65)
            .build();
}
