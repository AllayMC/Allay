package org.allaymc.api.block.interfaces.torch;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 *
 * TODO: Check can this one be in torch group?
 */
public interface BlockTorchflowerBehavior extends BlockBehavior {
  BlockType<BlockTorchflowerBehavior> TORCHFLOWER_TYPE = BlockTypeBuilder
          .builder(BlockTorchflowerBehavior.class)
          .vanillaBlock(VanillaBlockId.TORCHFLOWER)
          .build();
}
