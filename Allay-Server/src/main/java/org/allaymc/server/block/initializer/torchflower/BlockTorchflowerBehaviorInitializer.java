package org.allaymc.server.block.initializer.torchflower;

import org.allaymc.api.block.interfaces.torch.BlockTorchflowerBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockTorchflowerBehaviorInitializer {
  static void init() {
    BlockTypes.TORCHFLOWER_TYPE = BlockTypeBuilder
            .builder(BlockTorchflowerBehavior.class)
            .vanillaBlock(VanillaBlockId.TORCHFLOWER)
            .build();
  }
}
