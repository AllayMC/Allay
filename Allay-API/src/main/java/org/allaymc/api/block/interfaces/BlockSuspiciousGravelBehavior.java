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
public interface BlockSuspiciousGravelBehavior extends BlockBehavior {
  BlockType<BlockSuspiciousGravelBehavior> SUSPICIOUS_GRAVEL_TYPE = BlockTypeBuilder
          .builder(BlockSuspiciousGravelBehavior.class)
          .vanillaBlock(VanillaBlockId.SUSPICIOUS_GRAVEL)
          .setProperties(VanillaBlockPropertyTypes.BRUSHED_PROGRESS, VanillaBlockPropertyTypes.HANGING)
          .build();
}
