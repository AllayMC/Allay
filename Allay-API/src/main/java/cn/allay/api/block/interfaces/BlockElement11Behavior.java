package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement11Behavior extends BlockBehavior {
    BlockType<BlockElement11Behavior> ELEMENT_11_TYPE = BlockTypeBuilder
            .builder(BlockElement11Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_11)
            .build();
}
