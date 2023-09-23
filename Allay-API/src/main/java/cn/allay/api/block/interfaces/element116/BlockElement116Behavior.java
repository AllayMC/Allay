package cn.allay.api.block.interfaces.element116;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement116Behavior extends BlockBehavior {
    BlockType<BlockElement116Behavior> ELEMENT_116_TYPE = BlockTypeBuilder
            .builder(BlockElement116Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_116)
            .build();
}
