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
public interface BlockDarkOakPressurePlateBehavior extends BlockBehavior {
  BlockType<BlockDarkOakPressurePlateBehavior> DARK_OAK_PRESSURE_PLATE_TYPE = BlockTypeBuilder
          .builder(BlockDarkOakPressurePlateBehavior.class)
          .vanillaBlock(VanillaBlockId.DARK_OAK_PRESSURE_PLATE)
          .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .build();
}
