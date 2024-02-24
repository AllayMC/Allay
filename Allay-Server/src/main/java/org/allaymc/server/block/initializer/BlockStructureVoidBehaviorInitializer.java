package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockStructureVoidBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockStructureVoidBehaviorInitializer {
  static void init() {
    BlockTypes.STRUCTURE_VOID_TYPE = BlockTypeBuilder
            .builder(BlockStructureVoidBehavior.class)
            .vanillaBlock(VanillaBlockId.STRUCTURE_VOID)
            .setProperties(VanillaBlockPropertyTypes.STRUCTURE_VOID_TYPE)
            .build();
  }
}
