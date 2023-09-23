package cn.allay.api.block.interfaces.element18;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement18Behavior extends BlockBehavior {
    BlockType<BlockElement18Behavior> ELEMENT_18_TYPE = BlockTypeBuilder
            .builder(BlockElement18Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_18)
            .build();
}
