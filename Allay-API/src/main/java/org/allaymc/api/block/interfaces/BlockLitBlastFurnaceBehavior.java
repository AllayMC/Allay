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
public interface BlockLitBlastFurnaceBehavior extends BlockBehavior {
  BlockType<BlockLitBlastFurnaceBehavior> LIT_BLAST_FURNACE_TYPE = BlockTypeBuilder
          .builder(BlockLitBlastFurnaceBehavior.class)
          .vanillaBlock(VanillaBlockId.LIT_BLAST_FURNACE)
          .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
          .build();
}
