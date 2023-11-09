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
public interface BlockAcaciaPressurePlateBehavior extends BlockBehavior {
  BlockType<BlockAcaciaPressurePlateBehavior> ACACIA_PRESSURE_PLATE_TYPE = BlockTypeBuilder
          .builder(BlockAcaciaPressurePlateBehavior.class)
          .vanillaBlock(VanillaBlockId.ACACIA_PRESSURE_PLATE)
          .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .build();
}
