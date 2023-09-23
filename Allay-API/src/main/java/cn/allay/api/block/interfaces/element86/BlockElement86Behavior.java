package cn.allay.api.block.interfaces.element86;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement86Behavior extends BlockBehavior {
    BlockType<BlockElement86Behavior> ELEMENT_86_TYPE = BlockTypeBuilder
            .builder(BlockElement86Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_86)
            .build();
}
