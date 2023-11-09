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
public interface BlockNetherWartBehavior extends BlockBehavior {
  BlockType<BlockNetherWartBehavior> NETHER_WART_TYPE = BlockTypeBuilder
          .builder(BlockNetherWartBehavior.class)
          .vanillaBlock(VanillaBlockId.NETHER_WART)
          .setProperties(VanillaBlockPropertyTypes.AGE_4)
          .build();
}
