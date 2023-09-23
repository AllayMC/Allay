package cn.allay.api.block.interfaces.element24;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement24Behavior extends BlockBehavior {
    BlockType<BlockElement24Behavior> ELEMENT_24_TYPE = BlockTypeBuilder
            .builder(BlockElement24Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_24)
            .build();
}
