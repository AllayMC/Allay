package org.allaymc.server.block.initializer.standingsign;

import org.allaymc.api.block.interfaces.standingsign.BlockAcaciaStandingSignBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockAcaciaStandingSignBehaviorInitializer {
  static void init() {
    BlockTypes.ACACIA_STANDING_SIGN_TYPE = BlockTypeBuilder
            .builder(BlockAcaciaStandingSignBehavior.class)
            .vanillaBlock(VanillaBlockId.ACACIA_STANDING_SIGN)
            .setProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
            .build();
  }
}
