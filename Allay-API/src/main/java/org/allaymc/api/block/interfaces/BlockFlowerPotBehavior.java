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
public interface BlockFlowerPotBehavior extends BlockBehavior {
  BlockType<BlockFlowerPotBehavior> FLOWER_POT_TYPE = BlockTypeBuilder
          .builder(BlockFlowerPotBehavior.class)
          .vanillaBlock(VanillaBlockId.FLOWER_POT)
          .setProperties(VanillaBlockPropertyTypes.UPDATE_BIT)
          .build();
}
