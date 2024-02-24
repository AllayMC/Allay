package org.allaymc.server.block.initializer.hangingsign;

import org.allaymc.api.block.interfaces.hangingsign.BlockCrimsonHangingSignBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCrimsonHangingSignBehaviorInitializer {
  static void init() {
    BlockTypes.CRIMSON_HANGING_SIGN_TYPE = BlockTypeBuilder
            .builder(BlockCrimsonHangingSignBehavior.class)
            .vanillaBlock(VanillaBlockId.CRIMSON_HANGING_SIGN)
            .setProperties(VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION, VanillaBlockPropertyTypes.HANGING)
            .build();
  }
}
