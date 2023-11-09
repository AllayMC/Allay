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
public interface BlockFireBehavior extends BlockBehavior {
  BlockType<BlockFireBehavior> FIRE_TYPE = BlockTypeBuilder
          .builder(BlockFireBehavior.class)
          .vanillaBlock(VanillaBlockId.FIRE)
          .setProperties(VanillaBlockPropertyTypes.AGE_16)
          .build();
}
