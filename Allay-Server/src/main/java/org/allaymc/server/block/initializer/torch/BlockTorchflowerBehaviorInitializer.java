package org.allaymc.server.block.initializer.torch;

import org.allaymc.api.block.interfaces.torch.BlockTorchflowerBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.server.block.component.torch.BlockTorchBaseComponentImpl;

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
