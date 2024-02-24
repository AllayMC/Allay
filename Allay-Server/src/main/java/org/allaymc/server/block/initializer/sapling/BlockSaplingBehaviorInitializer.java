package org.allaymc.server.block.initializer.sapling;

import org.allaymc.api.block.interfaces.sapling.BlockSaplingBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockSaplingBehaviorInitializer {
  static void init() {
    BlockTypes.SAPLING_TYPE = BlockTypeBuilder
            .builder(BlockSaplingBehavior.class)
            .vanillaBlock(VanillaBlockId.SAPLING)
            .setProperties(VanillaBlockPropertyTypes.AGE_BIT, VanillaBlockPropertyTypes.SAPLING_TYPE)
            .build();
  }
}
