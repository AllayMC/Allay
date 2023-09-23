package cn.allay.api.block.interfaces.element7;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement7Behavior extends BlockBehavior {
    BlockType<BlockElement7Behavior> ELEMENT_7_TYPE = BlockTypeBuilder
            .builder(BlockElement7Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_7)
            .build();
}
