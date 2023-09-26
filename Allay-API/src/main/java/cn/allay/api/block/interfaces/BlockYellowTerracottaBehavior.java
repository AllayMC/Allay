package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockYellowTerracottaBehavior extends BlockBehavior {
    BlockType<BlockYellowTerracottaBehavior> YELLOW_TERRACOTTA_TYPE = BlockTypeBuilder
            .builder(BlockYellowTerracottaBehavior.class)
            .vanillaBlock(VanillaBlockId.YELLOW_TERRACOTTA)
            .build();
}
