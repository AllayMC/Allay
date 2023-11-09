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
public interface BlockChorusFlowerBehavior extends BlockBehavior {
  BlockType<BlockChorusFlowerBehavior> CHORUS_FLOWER_TYPE = BlockTypeBuilder
          .builder(BlockChorusFlowerBehavior.class)
          .vanillaBlock(VanillaBlockId.CHORUS_FLOWER)
          .setProperties(VanillaBlockPropertyTypes.AGE_6)
          .build();
}
