package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockTntBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockTntBehaviorInitializer {
  static void init() {
    BlockTypes.TNT_TYPE = BlockTypeBuilder
            .builder(BlockTntBehavior.class)
            .vanillaBlock(VanillaBlockId.TNT)
            .setProperties(VanillaBlockPropertyTypes.ALLOW_UNDERWATER_BIT, VanillaBlockPropertyTypes.EXPLODE_BIT)
            .build();
  }
}
