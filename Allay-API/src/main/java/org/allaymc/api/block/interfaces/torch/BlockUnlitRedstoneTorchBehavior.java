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
public interface BlockUnlitRedstoneTorchBehavior extends BlockBehavior {
  BlockType<BlockUnlitRedstoneTorchBehavior> UNLIT_REDSTONE_TORCH_TYPE = BlockTypeBuilder
          .builder(BlockUnlitRedstoneTorchBehavior.class)
          .vanillaBlock(VanillaBlockId.UNLIT_REDSTONE_TORCH)
          .setProperties(VanillaBlockPropertyTypes.TORCH_FACING_DIRECTION)
          .setBlockBaseComponentSupplier(getFactory()::createBlockTorchBaseComponent)
          .build();
}
