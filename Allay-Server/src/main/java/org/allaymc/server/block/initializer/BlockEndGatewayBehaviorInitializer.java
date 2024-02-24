package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockEndGatewayBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockEndGatewayBehaviorInitializer {
  static void init() {
    BlockTypes.END_GATEWAY_TYPE = BlockTypeBuilder
            .builder(BlockEndGatewayBehavior.class)
            .vanillaBlock(VanillaBlockId.END_GATEWAY)
            .build();
  }
}
