package cn.allay.api.block.interfaces.clay;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockClayBehavior extends BlockBehavior {
    BlockType<BlockClayBehavior> CLAY_TYPE = BlockTypeBuilder
            .builder(BlockClayBehavior.class)
            .vanillaBlock(VanillaBlockId.CLAY)
            .build();
}
