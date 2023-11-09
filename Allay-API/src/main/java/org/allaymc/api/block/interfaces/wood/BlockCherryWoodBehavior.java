package org.allaymc.api.block.interfaces.wood;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockCherryWoodBehavior extends BlockBehavior {
  BlockType<BlockCherryWoodBehavior> CHERRY_WOOD_TYPE = BlockTypeBuilder
          .builder(BlockCherryWoodBehavior.class)
          .vanillaBlock(VanillaBlockId.CHERRY_WOOD)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS, VanillaBlockPropertyTypes.STRIPPED_BIT)
          .build();
}
