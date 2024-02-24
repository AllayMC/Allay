package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockLitFurnaceBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLitFurnaceBehaviorInitializer {
  static void init() {
    BlockTypes.LIT_FURNACE_TYPE = BlockTypeBuilder
            .builder(BlockLitFurnaceBehavior.class)
            .vanillaBlock(VanillaBlockId.LIT_FURNACE)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
            .build();
  }
}
