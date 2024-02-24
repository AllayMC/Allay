package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockAmethystClusterBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockAmethystClusterBehaviorInitializer {
  static void init() {
    BlockTypes.AMETHYST_CLUSTER_TYPE = BlockTypeBuilder
            .builder(BlockAmethystClusterBehavior.class)
            .vanillaBlock(VanillaBlockId.AMETHYST_CLUSTER)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_BLOCK_FACE)
            .build();
  }
}
