package cn.allay.api.block.interfaces.element68;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement68Behavior extends BlockBehavior {
    BlockType<BlockElement68Behavior> ELEMENT_68_TYPE = BlockTypeBuilder
            .builder(BlockElement68Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_68)
            .build();
}
