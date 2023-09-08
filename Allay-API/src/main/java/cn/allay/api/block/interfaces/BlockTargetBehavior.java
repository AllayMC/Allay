package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockTargetBehavior extends BlockBehavior {
    BlockType<BlockTargetBehavior> TARGET_TYPE = BlockTypeBuilder
            .builder(BlockTargetBehavior.class)
            .vanillaBlock(VanillaBlockId.TARGET)
            .build();
}
