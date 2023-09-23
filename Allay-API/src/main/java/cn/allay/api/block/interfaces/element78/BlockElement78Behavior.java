package cn.allay.api.block.interfaces.element78;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement78Behavior extends BlockBehavior {
    BlockType<BlockElement78Behavior> ELEMENT_78_TYPE = BlockTypeBuilder
            .builder(BlockElement78Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_78)
            .build();
}
