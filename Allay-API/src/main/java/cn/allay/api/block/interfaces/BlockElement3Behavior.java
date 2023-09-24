package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement3Behavior extends BlockBehavior {
    BlockType<BlockElement3Behavior> ELEMENT_3_TYPE = BlockTypeBuilder
            .builder(BlockElement3Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_3)
            .build();
}
