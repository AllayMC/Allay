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
public interface BlockDispenserBehavior extends BlockBehavior {
  BlockType<BlockDispenserBehavior> DISPENSER_TYPE = BlockTypeBuilder
          .builder(BlockDispenserBehavior.class)
          .vanillaBlock(VanillaBlockId.DISPENSER)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION, VanillaBlockPropertyTypes.TRIGGERED_BIT)
          .build();
}
