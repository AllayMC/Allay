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
public interface BlockAmethystClusterBehavior extends BlockBehavior {
  BlockType<BlockAmethystClusterBehavior> AMETHYST_CLUSTER_TYPE = BlockTypeBuilder
          .builder(BlockAmethystClusterBehavior.class)
          .vanillaBlock(VanillaBlockId.AMETHYST_CLUSTER)
          .setProperties(VanillaBlockPropertyTypes.MINECRAFT_BLOCK_FACE)
          .build();
}
