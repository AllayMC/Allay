package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockCrafterBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCrafterBehaviorInitializer {
  static void init() {
    BlockTypes.CRAFTER_TYPE = BlockTypeBuilder
            .builder(BlockCrafterBehavior.class)
            .vanillaBlock(VanillaBlockId.CRAFTER)
            .setProperties(VanillaBlockPropertyTypes.CRAFTING, VanillaBlockPropertyTypes.ORIENTATION, VanillaBlockPropertyTypes.TRIGGERED_BIT)
            .build();
  }
}
