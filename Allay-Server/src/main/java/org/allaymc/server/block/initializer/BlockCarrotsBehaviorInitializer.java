package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockCarrotsBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockCarrotsBehaviorInitializer {
  static void init() {
    BlockTypes.CARROTS_TYPE = BlockTypeBuilder
            .builder(BlockCarrotsBehavior.class)
            .vanillaBlock(VanillaBlockId.CARROTS)
            .setProperties(VanillaBlockPropertyTypes.GROWTH)
            .build();
  }
}
