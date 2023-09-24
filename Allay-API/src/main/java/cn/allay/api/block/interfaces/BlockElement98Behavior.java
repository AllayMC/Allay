package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement98Behavior extends BlockBehavior {
    BlockType<BlockElement98Behavior> ELEMENT_98_TYPE = BlockTypeBuilder
            .builder(BlockElement98Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_98)
            .build();
}
