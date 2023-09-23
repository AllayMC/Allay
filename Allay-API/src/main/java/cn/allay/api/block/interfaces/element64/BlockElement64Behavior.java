package cn.allay.api.block.interfaces.element64;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement64Behavior extends BlockBehavior {
    BlockType<BlockElement64Behavior> ELEMENT_64_TYPE = BlockTypeBuilder
            .builder(BlockElement64Behavior.class)
            .vanillaBlock(VanillaBlockId.ELEMENT_64)
            .build();
}
