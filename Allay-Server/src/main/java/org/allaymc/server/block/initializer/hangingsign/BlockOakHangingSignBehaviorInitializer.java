package org.allaymc.server.block.initializer.hangingsign;

import org.allaymc.api.block.interfaces.hangingsign.BlockOakHangingSignBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockOakHangingSignBehaviorInitializer {
  static void init() {
    BlockTypes.OAK_HANGING_SIGN_TYPE = BlockTypeBuilder
            .builder(BlockOakHangingSignBehavior.class)
            .vanillaBlock(VanillaBlockId.OAK_HANGING_SIGN)
            .setProperties(VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION, VanillaBlockPropertyTypes.HANGING)
            .build();
  }
}
