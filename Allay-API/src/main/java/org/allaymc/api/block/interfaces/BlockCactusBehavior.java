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
public interface BlockCactusBehavior extends BlockBehavior {
  BlockType<BlockCactusBehavior> CACTUS_TYPE = BlockTypeBuilder
          .builder(BlockCactusBehavior.class)
          .vanillaBlock(VanillaBlockId.CACTUS)
          .setProperties(VanillaBlockPropertyTypes.AGE_16)
          .build();
}
