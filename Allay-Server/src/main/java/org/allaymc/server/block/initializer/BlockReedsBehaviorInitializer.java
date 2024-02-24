package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockReedsBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockReedsBehaviorInitializer {
  static void init() {
    BlockTypes.REEDS_TYPE = BlockTypeBuilder
            .builder(BlockReedsBehavior.class)
            .vanillaBlock(VanillaBlockId.REEDS)
            .setProperties(VanillaBlockPropertyTypes.AGE_16)
            .build();
  }
}
