package cn.allay.api.block.interfaces.element107;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement107Behavior extends BlockBehavior {
    BlockType<BlockElement107Behavior> ELEMENT_107_TYPE = BlockTypeBuilder
            .builder(BlockElement107Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_107)
            .build();
}
