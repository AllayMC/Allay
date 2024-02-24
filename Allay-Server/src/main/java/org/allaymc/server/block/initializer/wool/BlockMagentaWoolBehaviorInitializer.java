package org.allaymc.server.block.initializer.wool;

import org.allaymc.api.block.interfaces.wool.BlockMagentaWoolBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockMagentaWoolBehaviorInitializer {
  static void init() {
    BlockTypes.MAGENTA_WOOL_TYPE = BlockTypeBuilder
            .builder(BlockMagentaWoolBehavior.class)
            .vanillaBlock(VanillaBlockId.MAGENTA_WOOL)
            .build();
  }
}
