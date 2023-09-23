package cn.allay.api.block.interfaces.element26;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement26Behavior extends BlockBehavior {
    BlockType<BlockElement26Behavior> ELEMENT_26_TYPE = BlockTypeBuilder
            .builder(BlockElement26Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_26)
            .build();
}
