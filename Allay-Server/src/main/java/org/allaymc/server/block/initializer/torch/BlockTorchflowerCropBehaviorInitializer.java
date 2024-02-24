package org.allaymc.server.block.initializer.torch;

import org.allaymc.api.block.interfaces.torch.BlockTorchflowerCropBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;
import org.allaymc.server.block.component.torch.BlockTorchBaseComponentImpl;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockTorchflowerCropBehaviorInitializer {
  static void init() {
    BlockTypes.TORCHFLOWER_CROP_TYPE = BlockTypeBuilder
            .builder(BlockTorchflowerCropBehavior.class)
            .vanillaBlock(VanillaBlockId.TORCHFLOWER_CROP)
            .setProperties(VanillaBlockPropertyTypes.GROWTH)
            .setBlockBaseComponentSupplier(BlockTorchBaseComponentImpl::new)
            .build();
  }
}
