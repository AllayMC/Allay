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
public interface BlockSprucePressurePlateBehavior extends BlockBehavior {
  BlockType<BlockSprucePressurePlateBehavior> SPRUCE_PRESSURE_PLATE_TYPE = BlockTypeBuilder
          .builder(BlockSprucePressurePlateBehavior.class)
          .vanillaBlock(VanillaBlockId.SPRUCE_PRESSURE_PLATE)
          .setProperties(VanillaBlockPropertyTypes.REDSTONE_SIGNAL)
          .build();
}
