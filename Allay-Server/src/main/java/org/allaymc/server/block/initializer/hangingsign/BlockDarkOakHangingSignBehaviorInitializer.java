package org.allaymc.server.block.initializer.hangingsign;

import org.allaymc.api.block.interfaces.hangingsign.BlockDarkOakHangingSignBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockDarkOakHangingSignBehaviorInitializer {
  static void init() {
    BlockTypes.DARK_OAK_HANGING_SIGN_TYPE = BlockTypeBuilder
            .builder(BlockDarkOakHangingSignBehavior.class)
            .vanillaBlock(VanillaBlockId.DARK_OAK_HANGING_SIGN)
            .setProperties(VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION, VanillaBlockPropertyTypes.HANGING)
            .build();
  }
}
