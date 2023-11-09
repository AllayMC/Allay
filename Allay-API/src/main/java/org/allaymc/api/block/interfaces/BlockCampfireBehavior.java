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
public interface BlockCampfireBehavior extends BlockBehavior {
  BlockType<BlockCampfireBehavior> CAMPFIRE_TYPE = BlockTypeBuilder
          .builder(BlockCampfireBehavior.class)
          .vanillaBlock(VanillaBlockId.CAMPFIRE)
          .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, VanillaBlockPropertyTypes.EXTINGUISHED)
          .build();
}
