package cn.allay.api.block.interfaces.wallbanner;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockWallBannerBehavior extends BlockBehavior {
    BlockType<BlockWallBannerBehavior> WALL_BANNER_TYPE = BlockTypeBuilder
            .builder(BlockWallBannerBehavior.class)
            .vanillaBlock(VanillaBlockId.WALL_BANNER)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
}
