package org.allaymc.server.block.initializer.sapling;

import org.allaymc.api.block.interfaces.sapling.BlockBambooSaplingBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBambooSaplingBehaviorInitializer {
  static void init() {
    BlockTypes.BAMBOO_SAPLING_TYPE = BlockTypeBuilder
            .builder(BlockBambooSaplingBehavior.class)
            .vanillaBlock(VanillaBlockId.BAMBOO_SAPLING)
            .setProperties(VanillaBlockPropertyTypes.AGE_BIT, VanillaBlockPropertyTypes.SAPLING_TYPE)
            .build();
  }
}
