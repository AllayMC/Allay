package org.allaymc.server.block.initializer.standingsign;

import org.allaymc.api.block.interfaces.standingsign.BlockBirchStandingSignBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBirchStandingSignBehaviorInitializer {
  static void init() {
    BlockTypes.BIRCH_STANDING_SIGN_TYPE = BlockTypeBuilder
            .builder(BlockBirchStandingSignBehavior.class)
            .vanillaBlock(VanillaBlockId.BIRCH_STANDING_SIGN)
            .setProperties(VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION)
            .build();
  }
}
