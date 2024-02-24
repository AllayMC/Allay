package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockWebBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockWebBehaviorInitializer {
  static void init() {
    BlockTypes.WEB_TYPE = BlockTypeBuilder
            .builder(BlockWebBehavior.class)
            .vanillaBlock(VanillaBlockId.WEB)
            .build();
  }
}
