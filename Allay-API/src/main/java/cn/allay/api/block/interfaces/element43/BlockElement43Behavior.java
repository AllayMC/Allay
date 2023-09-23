package cn.allay.api.block.interfaces.element43;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement43Behavior extends BlockBehavior {
    BlockType<BlockElement43Behavior> ELEMENT_43_TYPE = BlockTypeBuilder
            .builder(BlockElement43Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_43)
            .build();
}
