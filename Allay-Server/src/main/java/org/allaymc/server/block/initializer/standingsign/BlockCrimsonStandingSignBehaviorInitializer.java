package org.allaymc.server.block.initializer.standingsign;

import org.allaymc.api.block.interfaces.standingsign.BlockCrimsonStandingSignBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCrimsonStandingSignBehaviorInitializer {
  static void init() {
    BlockTypes.CRIMSON_STANDING_SIGN_TYPE = BlockTypeBuilder
            .builder(BlockCrimsonStandingSignBehavior.class)
            .vanillaBlock(VanillaBlockId.CRIMSON_STANDING_SIGN)
            .setProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
            .build();
  }
}
