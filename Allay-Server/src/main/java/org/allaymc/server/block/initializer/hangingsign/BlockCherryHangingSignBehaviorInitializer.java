package org.allaymc.server.block.initializer.hangingsign;

import org.allaymc.api.block.interfaces.hangingsign.BlockCherryHangingSignBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCherryHangingSignBehaviorInitializer {
  static void init() {
    BlockTypes.CHERRY_HANGING_SIGN_TYPE = BlockTypeBuilder
            .builder(BlockCherryHangingSignBehavior.class)
            .vanillaBlock(VanillaBlockId.CHERRY_HANGING_SIGN)
            .setProperties(VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION, VanillaBlockPropertyTypes.HANGING)
            .build();
  }
}
