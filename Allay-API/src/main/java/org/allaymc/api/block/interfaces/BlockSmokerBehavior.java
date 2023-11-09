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
public interface BlockSmokerBehavior extends BlockBehavior {
  BlockType<BlockSmokerBehavior> SMOKER_TYPE = BlockTypeBuilder
          .builder(BlockSmokerBehavior.class)
          .vanillaBlock(VanillaBlockId.SMOKER)
          .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
          .build();
}
