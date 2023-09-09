package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement89Behavior extends BlockBehavior {
    BlockType<BlockElement89Behavior> ELEMENT_89_TYPE = BlockTypeBuilder
            .builder(BlockElement89Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_89)
            .build();
}
