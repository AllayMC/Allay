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
public interface BlockStrippedCherryWoodBehavior extends BlockBehavior {
  BlockType<BlockStrippedCherryWoodBehavior> STRIPPED_CHERRY_WOOD_TYPE = BlockTypeBuilder
          .builder(BlockStrippedCherryWoodBehavior.class)
          .vanillaBlock(VanillaBlockId.STRIPPED_CHERRY_WOOD)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .build();
}
