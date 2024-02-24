package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockSnifferEggBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockSnifferEggBehaviorInitializer {
  static void init() {
    BlockTypes.SNIFFER_EGG_TYPE = BlockTypeBuilder
            .builder(BlockSnifferEggBehavior.class)
            .vanillaBlock(VanillaBlockId.SNIFFER_EGG)
            .setProperties(VanillaBlockPropertyTypes.CRACKED_STATE)
            .build();
  }
}
