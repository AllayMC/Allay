package cn.allay.api.block.interfaces.element112;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement112Behavior extends BlockBehavior {
    BlockType<BlockElement112Behavior> ELEMENT_112_TYPE = BlockTypeBuilder
            .builder(BlockElement112Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_112)
            .build();
}
