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
public interface BlockMangroveWoodBehavior extends BlockBehavior {
  BlockType<BlockMangroveWoodBehavior> MANGROVE_WOOD_TYPE = BlockTypeBuilder
          .builder(BlockMangroveWoodBehavior.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_WOOD)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS, VanillaBlockPropertyTypes.STRIPPED_BIT)
          .build();
}
