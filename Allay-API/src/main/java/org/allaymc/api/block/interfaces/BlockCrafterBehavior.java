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
public interface BlockCrafterBehavior extends BlockBehavior {
  BlockType<BlockCrafterBehavior> CRAFTER_TYPE = BlockTypeBuilder
          .builder(BlockCrafterBehavior.class)
          .vanillaBlock(VanillaBlockId.CRAFTER)
          .setProperties(VanillaBlockPropertyTypes.CRAFTING, VanillaBlockPropertyTypes.ORIENTATION, VanillaBlockPropertyTypes.TRIGGERED_BIT)
          .build();
}
