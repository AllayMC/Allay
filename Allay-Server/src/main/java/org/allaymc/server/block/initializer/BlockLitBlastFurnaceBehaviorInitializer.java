package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockLitBlastFurnaceBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLitBlastFurnaceBehaviorInitializer {
  static void init() {
    BlockTypes.LIT_BLAST_FURNACE_TYPE = BlockTypeBuilder
            .builder(BlockLitBlastFurnaceBehavior.class)
            .vanillaBlock(VanillaBlockId.LIT_BLAST_FURNACE)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
            .build();
  }
}
