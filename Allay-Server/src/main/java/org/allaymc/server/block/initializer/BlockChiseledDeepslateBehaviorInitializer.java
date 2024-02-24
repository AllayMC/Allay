package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockChiseledDeepslateBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockChiseledDeepslateBehaviorInitializer {
  static void init() {
    BlockTypes.CHISELED_DEEPSLATE_TYPE = BlockTypeBuilder
            .builder(BlockChiseledDeepslateBehavior.class)
            .vanillaBlock(VanillaBlockId.CHISELED_DEEPSLATE)
            .build();
  }
}
