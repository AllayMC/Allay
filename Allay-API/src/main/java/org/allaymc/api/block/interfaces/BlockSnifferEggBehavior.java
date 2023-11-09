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
public interface BlockSnifferEggBehavior extends BlockBehavior {
  BlockType<BlockSnifferEggBehavior> SNIFFER_EGG_TYPE = BlockTypeBuilder
          .builder(BlockSnifferEggBehavior.class)
          .vanillaBlock(VanillaBlockId.SNIFFER_EGG)
          .setProperties(VanillaBlockPropertyTypes.CRACKED_STATE)
          .build();
}
