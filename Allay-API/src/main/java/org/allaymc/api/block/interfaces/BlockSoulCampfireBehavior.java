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
public interface BlockSoulCampfireBehavior extends BlockBehavior {
  BlockType<BlockSoulCampfireBehavior> SOUL_CAMPFIRE_TYPE = BlockTypeBuilder
          .builder(BlockSoulCampfireBehavior.class)
          .vanillaBlock(VanillaBlockId.SOUL_CAMPFIRE)
          .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, VanillaBlockPropertyTypes.EXTINGUISHED)
          .build();
}
