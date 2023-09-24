package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockWarpedNyliumBehavior extends BlockBehavior {
    BlockType<BlockWarpedNyliumBehavior> WARPED_NYLIUM_TYPE = BlockTypeBuilder
            .builder(BlockWarpedNyliumBehavior.class)
            .vanillaBlock(VanillaBlockId.WARPED_NYLIUM)
            .build();
}
