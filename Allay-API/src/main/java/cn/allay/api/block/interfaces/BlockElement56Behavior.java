package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement56Behavior extends BlockBehavior {
    BlockType<BlockElement56Behavior> ELEMENT_56_TYPE = BlockTypeBuilder
            .builder(BlockElement56Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_56)
            .build();
}
