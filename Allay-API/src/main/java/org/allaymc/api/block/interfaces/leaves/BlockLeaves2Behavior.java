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
public interface BlockLeaves2Behavior extends BlockBehavior {
  BlockType<BlockLeaves2Behavior> LEAVES2_TYPE = BlockTypeBuilder
          .builder(BlockLeaves2Behavior.class)
          .vanillaBlock(VanillaBlockId.LEAVES2)
          .setProperties(VanillaBlockPropertyTypes.NEW_LEAF_TYPE, VanillaBlockPropertyTypes.PERSISTENT_BIT, VanillaBlockPropertyTypes.UPDATE_BIT)
          .build();
}
