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
public interface BlockSculkCatalystBehavior extends BlockBehavior {
  BlockType<BlockSculkCatalystBehavior> SCULK_CATALYST_TYPE = BlockTypeBuilder
          .builder(BlockSculkCatalystBehavior.class)
          .vanillaBlock(VanillaBlockId.SCULK_CATALYST)
          .setProperties(VanillaBlockPropertyTypes.BLOOM)
          .build();
}
