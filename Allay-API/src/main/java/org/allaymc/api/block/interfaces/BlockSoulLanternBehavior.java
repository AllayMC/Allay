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
public interface BlockSoulLanternBehavior extends BlockBehavior {
  BlockType<BlockSoulLanternBehavior> SOUL_LANTERN_TYPE = BlockTypeBuilder
          .builder(BlockSoulLanternBehavior.class)
          .vanillaBlock(VanillaBlockId.SOUL_LANTERN)
          .setProperties(VanillaBlockPropertyTypes.HANGING)
          .build();
}
