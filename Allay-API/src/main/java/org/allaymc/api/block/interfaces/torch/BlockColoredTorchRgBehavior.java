package org.allaymc.api.block.interfaces.torch;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockColoredTorchRgBehavior extends BlockBehavior {
  BlockType<BlockColoredTorchRgBehavior> COLORED_TORCH_RG_TYPE = BlockTypeBuilder
          .builder(BlockColoredTorchRgBehavior.class)
          .vanillaBlock(VanillaBlockId.COLORED_TORCH_RG)
          .setProperties(VanillaBlockPropertyTypes.COLOR_BIT, VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION)
          .setBlockBaseComponentSupplier(BlockTorchBaseComponentImpl::new)
          .build();
}
