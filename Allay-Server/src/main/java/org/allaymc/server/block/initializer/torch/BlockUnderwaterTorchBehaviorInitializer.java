package org.allaymc.server.block.initializer.torch;

import org.allaymc.api.block.interfaces.torch.BlockUnderwaterTorchBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;
import org.allaymc.server.block.component.torch.BlockTorchBaseComponentImpl;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockUnderwaterTorchBehaviorInitializer {
  static void init() {
    BlockTypes.UNDERWATER_TORCH_TYPE = BlockTypeBuilder
            .builder(BlockUnderwaterTorchBehavior.class)
            .vanillaBlock(VanillaBlockId.UNDERWATER_TORCH)
            .setProperties(VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION)
            .setBlockBaseComponentSupplier(BlockTorchBaseComponentImpl::new)
            .build();
  }
}
