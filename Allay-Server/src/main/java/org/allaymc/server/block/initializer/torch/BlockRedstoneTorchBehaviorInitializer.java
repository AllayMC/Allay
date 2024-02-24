package org.allaymc.server.block.initializer.torch;

import org.allaymc.api.block.interfaces.torch.BlockRedstoneTorchBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;
import org.allaymc.server.block.component.torch.BlockTorchBaseComponentImpl;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockRedstoneTorchBehaviorInitializer {
  static void init() {
    BlockTypes.REDSTONE_TORCH_TYPE = BlockTypeBuilder
            .builder(BlockRedstoneTorchBehavior.class)
            .vanillaBlock(VanillaBlockId.REDSTONE_TORCH)
            .setProperties(VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION)
            .setBlockBaseComponentSupplier(BlockTorchBaseComponentImpl::new)
            .build();
  }
}
