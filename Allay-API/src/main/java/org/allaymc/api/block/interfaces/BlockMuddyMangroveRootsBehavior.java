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
public interface BlockMuddyMangroveRootsBehavior extends BlockBehavior {
  BlockType<BlockMuddyMangroveRootsBehavior> MUDDY_MANGROVE_ROOTS_TYPE = BlockTypeBuilder
          .builder(BlockMuddyMangroveRootsBehavior.class)
          .vanillaBlock(VanillaBlockId.MUDDY_MANGROVE_ROOTS)
          .setProperties(VanillaBlockPropertyTypes.PILLAR_AXIS)
          .build();
}
