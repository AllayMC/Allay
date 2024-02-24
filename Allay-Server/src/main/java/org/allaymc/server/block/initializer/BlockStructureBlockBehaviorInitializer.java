package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockStructureBlockBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockStructureBlockBehaviorInitializer {
  static void init() {
    BlockTypes.STRUCTURE_BLOCK_TYPE = BlockTypeBuilder
            .builder(BlockStructureBlockBehavior.class)
            .vanillaBlock(VanillaBlockId.STRUCTURE_BLOCK)
            .setProperties(VanillaBlockPropertyTypes.STRUCTURE_BLOCK_TYPE)
            .build();
  }
}
