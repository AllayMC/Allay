package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockPinkPetalsBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockPinkPetalsBehaviorInitializer {
  static void init() {
    BlockTypes.PINK_PETALS_TYPE = BlockTypeBuilder
            .builder(BlockPinkPetalsBehavior.class)
            .vanillaBlock(VanillaBlockId.PINK_PETALS)
            .setProperties(VanillaBlockPropertyTypes.GROWTH, VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
            .build();
  }
}
