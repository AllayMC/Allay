package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockStandingBannerBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockStandingBannerBehaviorInitializer {
  static void init() {
    BlockTypes.STANDING_BANNER_TYPE = BlockTypeBuilder
            .builder(BlockStandingBannerBehavior.class)
            .vanillaBlock(VanillaBlockId.STANDING_BANNER)
            .setProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
            .build();
  }
}
