package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockLightningRodBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLightningRodBehaviorInitializer {
  static void init() {
    BlockTypes.LIGHTNING_ROD_TYPE = BlockTypeBuilder
            .builder(BlockLightningRodBehavior.class)
            .vanillaBlock(VanillaBlockId.LIGHTNING_ROD)
            .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
            .build();
  }
}
