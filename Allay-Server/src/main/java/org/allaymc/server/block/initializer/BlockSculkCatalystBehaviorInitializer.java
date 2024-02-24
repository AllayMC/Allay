package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockSculkCatalystBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockSculkCatalystBehaviorInitializer {
  static void init() {
    BlockTypes.SCULK_CATALYST_TYPE = BlockTypeBuilder
            .builder(BlockSculkCatalystBehavior.class)
            .vanillaBlock(VanillaBlockId.SCULK_CATALYST)
            .setProperties(VanillaBlockPropertyTypes.BLOOM)
            .build();
  }
}
