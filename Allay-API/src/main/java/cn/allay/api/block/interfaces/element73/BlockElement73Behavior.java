package cn.allay.api.block.interfaces.element73;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement73Behavior extends BlockBehavior {
    BlockType<BlockElement73Behavior> ELEMENT_73_TYPE = BlockTypeBuilder
            .builder(BlockElement73Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_73)
            .build();
}
