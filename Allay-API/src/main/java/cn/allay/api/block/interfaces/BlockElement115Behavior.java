package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement115Behavior extends BlockBehavior {
    BlockType<BlockElement115Behavior> ELEMENT_115_TYPE = BlockTypeBuilder
            .builder(BlockElement115Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_115)
            .build();
}
