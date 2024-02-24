package org.allaymc.server.block.initializer.hangingsign;

import org.allaymc.api.block.interfaces.hangingsign.BlockMangroveHangingSignBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockMangroveHangingSignBehaviorInitializer {
  static void init() {
    BlockTypes.MANGROVE_HANGING_SIGN_TYPE = BlockTypeBuilder
            .builder(BlockMangroveHangingSignBehavior.class)
            .vanillaBlock(VanillaBlockId.MANGROVE_HANGING_SIGN)
            .setProperties(VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.GROUND_SIGN_DIRECTION, VanillaBlockPropertyTypes.HANGING)
            .build();
  }
}
