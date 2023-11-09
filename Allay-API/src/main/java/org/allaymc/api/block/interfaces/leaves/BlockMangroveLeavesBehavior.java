package org.allaymc.api.block.interfaces.leaves;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockMangroveLeavesBehavior extends BlockBehavior {
  BlockType<BlockMangroveLeavesBehavior> MANGROVE_LEAVES_TYPE = BlockTypeBuilder
          .builder(BlockMangroveLeavesBehavior.class)
          .vanillaBlock(VanillaBlockId.MANGROVE_LEAVES)
          .setProperties(VanillaBlockPropertyTypes.PERSISTENT_BIT, VanillaBlockPropertyTypes.UPDATE_BIT)
          .build();
}
