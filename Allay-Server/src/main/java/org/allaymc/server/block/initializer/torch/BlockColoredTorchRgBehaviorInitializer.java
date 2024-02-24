package org.allaymc.server.block.initializer.torch;

import org.allaymc.api.block.interfaces.torch.BlockColoredTorchRgBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;
import org.allaymc.server.block.component.torch.BlockTorchBaseComponentImpl;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockColoredTorchRgBehaviorInitializer {
  static void init() {
    BlockTypes.COLORED_TORCH_RG_TYPE = BlockTypeBuilder
            .builder(BlockColoredTorchRgBehavior.class)
            .vanillaBlock(VanillaBlockId.COLORED_TORCH_RG)
            .setProperties(VanillaBlockPropertyTypes.COLOR_BIT, VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION)
            .setBlockBaseComponentSupplier(BlockTorchBaseComponentImpl::new)
            .build();
  }
}
