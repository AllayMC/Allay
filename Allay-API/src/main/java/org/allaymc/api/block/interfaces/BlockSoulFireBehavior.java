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
public interface BlockSoulFireBehavior extends BlockBehavior {
  BlockType<BlockSoulFireBehavior> SOUL_FIRE_TYPE = BlockTypeBuilder
          .builder(BlockSoulFireBehavior.class)
          .vanillaBlock(VanillaBlockId.SOUL_FIRE)
          .setProperties(VanillaBlockPropertyTypes.AGE_16)
          .build();
}
