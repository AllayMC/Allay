package cn.allay.api.block.interfaces.element70;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement70Behavior extends BlockBehavior {
    BlockType<BlockElement70Behavior> ELEMENT_70_TYPE = BlockTypeBuilder
            .builder(BlockElement70Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_70)
            .build();
}
