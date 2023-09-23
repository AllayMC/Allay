package cn.allay.api.block.interfaces.element97;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement97Behavior extends BlockBehavior {
    BlockType<BlockElement97Behavior> ELEMENT_97_TYPE = BlockTypeBuilder
            .builder(BlockElement97Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_97)
            .build();
}
