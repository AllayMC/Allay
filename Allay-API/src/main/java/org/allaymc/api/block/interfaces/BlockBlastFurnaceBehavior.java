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
public interface BlockBlastFurnaceBehavior extends BlockBehavior {
  BlockType<BlockBlastFurnaceBehavior> BLAST_FURNACE_TYPE = BlockTypeBuilder
          .builder(BlockBlastFurnaceBehavior.class)
          .vanillaBlock(VanillaBlockId.BLAST_FURNACE)
          .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
          .build();
}
