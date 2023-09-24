package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement104Behavior extends BlockBehavior {
    BlockType<BlockElement104Behavior> ELEMENT_104_TYPE = BlockTypeBuilder
            .builder(BlockElement104Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_104)
            .build();
}
