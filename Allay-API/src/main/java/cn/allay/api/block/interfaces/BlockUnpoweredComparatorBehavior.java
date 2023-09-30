package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockUnpoweredComparatorBehavior extends BlockBehavior {
  BlockType<BlockUnpoweredComparatorBehavior> UNPOWERED_COMPARATOR_TYPE = BlockTypeBuilder
          .builder(BlockUnpoweredComparatorBehavior.class)
          .vanillaBlock(VanillaBlockId.UNPOWERED_COMPARATOR)
          .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, VanillaBlockPropertyTypes.OUTPUT_LIT_BIT, VanillaBlockPropertyTypes.OUTPUT_SUBTRACT_BIT)
          .build();
}
