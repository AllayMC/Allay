package cn.allay.api.block.interfaces.hardenedclay;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockHardenedClayBehavior extends BlockBehavior {
    BlockType<BlockHardenedClayBehavior> HARDENED_CLAY_TYPE = BlockTypeBuilder
            .builder(BlockHardenedClayBehavior.class)
            .vanillaBlock(VanillaBlockId.HARDENED_CLAY)
            .build();
}
