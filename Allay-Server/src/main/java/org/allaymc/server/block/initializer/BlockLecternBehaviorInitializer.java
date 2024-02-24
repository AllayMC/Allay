package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockLecternBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockLecternBehaviorInitializer {
  static void init() {
    BlockTypes.LECTERN_TYPE = BlockTypeBuilder
            .builder(BlockLecternBehavior.class)
            .vanillaBlock(VanillaBlockId.LECTERN)
            .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, VanillaBlockPropertyTypes.POWERED_BIT)
            .build();
  }
}
