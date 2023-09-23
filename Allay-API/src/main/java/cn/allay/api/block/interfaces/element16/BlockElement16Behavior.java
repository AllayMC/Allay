package cn.allay.api.block.interfaces.element16;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement16Behavior extends BlockBehavior {
    BlockType<BlockElement16Behavior> ELEMENT_16_TYPE = BlockTypeBuilder
            .builder(BlockElement16Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_16)
            .build();
}
