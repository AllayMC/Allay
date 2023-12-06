package org.allaymc.api.block.interfaces.torch;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

import static org.allaymc.api.block.component.BlockComponentImplFactory.getFactory;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockColoredTorchBpBehavior extends BlockBehavior {
  BlockType<BlockColoredTorchBpBehavior> COLORED_TORCH_BP_TYPE = BlockTypeBuilder
          .builder(BlockColoredTorchBpBehavior.class)
          .vanillaBlock(VanillaBlockId.COLORED_TORCH_BP)
          .setProperties(VanillaBlockPropertyTypes.COLOR_BIT, VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION)
          .setBlockBaseComponentSupplier(getFactory()::createBlockTorchBaseComponent)
          .build();
}
