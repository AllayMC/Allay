package org.allaymc.api.block.interfaces.slab;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockExposedCutCopperSlabBehavior extends BlockBehavior {
  BlockType<BlockExposedCutCopperSlabBehavior> EXPOSED_CUT_COPPER_SLAB_TYPE = BlockTypeBuilder
          .builder(BlockExposedCutCopperSlabBehavior.class)
          .vanillaBlock(VanillaBlockId.EXPOSED_CUT_COPPER_SLAB)
          .setProperties(VanillaBlockPropertyTypes.MINECRAFT_VERTICAL_HALF)
          .build();
}
