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
public interface BlockSuspiciousSandBehavior extends BlockBehavior {
  BlockType<BlockSuspiciousSandBehavior> SUSPICIOUS_SAND_TYPE = BlockTypeBuilder
          .builder(BlockSuspiciousSandBehavior.class)
          .vanillaBlock(VanillaBlockId.SUSPICIOUS_SAND)
          .setProperties(VanillaBlockPropertyTypes.BRUSHED_PROGRESS, VanillaBlockPropertyTypes.HANGING)
          .build();
}
