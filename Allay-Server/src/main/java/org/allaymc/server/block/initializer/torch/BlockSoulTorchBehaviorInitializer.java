package org.allaymc.server.block.initializer.torch;

import org.allaymc.api.block.interfaces.torch.BlockSoulTorchBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;
import org.allaymc.server.block.component.torch.BlockTorchBaseComponentImpl;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockSoulTorchBehaviorInitializer {
  static void init() {
    BlockTypes.SOUL_TORCH_TYPE = BlockTypeBuilder
            .builder(BlockSoulTorchBehavior.class)
            .vanillaBlock(VanillaBlockId.SOUL_TORCH)
            .setProperties(VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION)
            .setBlockBaseComponentSupplier(BlockTorchBaseComponentImpl::new)
            .build();
  }
}
