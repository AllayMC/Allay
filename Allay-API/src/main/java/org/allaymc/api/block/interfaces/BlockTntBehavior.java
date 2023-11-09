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
public interface BlockTntBehavior extends BlockBehavior {
  BlockType<BlockTntBehavior> TNT_TYPE = BlockTypeBuilder
          .builder(BlockTntBehavior.class)
          .vanillaBlock(VanillaBlockId.TNT)
          .setProperties(VanillaBlockPropertyTypes.ALLOW_UNDERWATER_BIT, VanillaBlockPropertyTypes.EXPLODE_BIT)
          .build();
}
