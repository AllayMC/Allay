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
public interface BlockPointedDripstoneBehavior extends BlockBehavior {
  BlockType<BlockPointedDripstoneBehavior> POINTED_DRIPSTONE_TYPE = BlockTypeBuilder
          .builder(BlockPointedDripstoneBehavior.class)
          .vanillaBlock(VanillaBlockId.POINTED_DRIPSTONE)
          .setProperties(VanillaBlockPropertyTypes.DRIPSTONE_THICKNESS, VanillaBlockPropertyTypes.HANGING)
          .build();
}
