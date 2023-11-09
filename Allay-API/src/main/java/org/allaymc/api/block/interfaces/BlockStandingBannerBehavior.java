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
public interface BlockStandingBannerBehavior extends BlockBehavior {
  BlockType<BlockStandingBannerBehavior> STANDING_BANNER_TYPE = BlockTypeBuilder
          .builder(BlockStandingBannerBehavior.class)
          .vanillaBlock(VanillaBlockId.STANDING_BANNER)
          .setProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
          .build();
}
