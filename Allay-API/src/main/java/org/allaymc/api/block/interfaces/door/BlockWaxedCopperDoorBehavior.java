package org.allaymc.api.block.interfaces.door;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockWaxedCopperDoorBehavior extends BlockBehavior {
  BlockType<BlockWaxedCopperDoorBehavior> WAXED_COPPER_DOOR_TYPE = BlockTypeBuilder
          .builder(BlockWaxedCopperDoorBehavior.class)
          .vanillaBlock(VanillaBlockId.WAXED_COPPER_DOOR)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
          .build();
}
