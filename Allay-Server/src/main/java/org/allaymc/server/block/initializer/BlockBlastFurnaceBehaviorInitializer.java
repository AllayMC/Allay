package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockBlastFurnaceBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockBlastFurnaceBehaviorInitializer {
  static void init() {
    BlockTypes.BLAST_FURNACE_TYPE = BlockTypeBuilder
            .builder(BlockBlastFurnaceBehavior.class)
            .vanillaBlock(VanillaBlockId.BLAST_FURNACE)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
            .build();
  }
}
