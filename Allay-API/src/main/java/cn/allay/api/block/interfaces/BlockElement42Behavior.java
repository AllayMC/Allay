package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement42Behavior extends BlockBehavior {
    BlockType<BlockElement42Behavior> ELEMENT_42_TYPE = BlockTypeBuilder
            .builder(BlockElement42Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_42)
            .build();
}
