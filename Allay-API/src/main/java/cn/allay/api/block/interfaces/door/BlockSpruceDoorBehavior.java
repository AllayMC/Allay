package cn.allay.api.block.interfaces.door;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockSpruceDoorBehavior extends BlockBehavior {
  BlockType<BlockSpruceDoorBehavior> SPRUCE_DOOR_TYPE = BlockTypeBuilder
          .builder(BlockSpruceDoorBehavior.class)
          .vanillaBlock(VanillaBlockId.SPRUCE_DOOR)
          .setProperties(VanillaBlockPropertyTypes.DIRECTION, VanillaBlockPropertyTypes.DOOR_HINGE_BIT, VanillaBlockPropertyTypes.OPEN_BIT, VanillaBlockPropertyTypes.UPPER_BLOCK_BIT)
          .build();
}
