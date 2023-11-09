package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

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
