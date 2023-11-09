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
public interface BlockKelpBehavior extends BlockBehavior {
  BlockType<BlockKelpBehavior> KELP_TYPE = BlockTypeBuilder
          .builder(BlockKelpBehavior.class)
          .vanillaBlock(VanillaBlockId.KELP)
          .setProperties(VanillaBlockPropertyTypes.KELP_AGE)
          .build();
}
