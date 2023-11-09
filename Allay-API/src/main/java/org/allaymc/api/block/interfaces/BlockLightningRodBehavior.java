package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockLightningRodBehavior extends BlockBehavior {
  BlockType<BlockLightningRodBehavior> LIGHTNING_ROD_TYPE = BlockTypeBuilder
          .builder(BlockLightningRodBehavior.class)
          .vanillaBlock(VanillaBlockId.LIGHTNING_ROD)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .build();
}
