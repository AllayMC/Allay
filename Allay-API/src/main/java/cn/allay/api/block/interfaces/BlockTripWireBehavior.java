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
public interface BlockTripWireBehavior extends BlockBehavior {
  BlockType<BlockTripWireBehavior> TRIP_WIRE_TYPE = BlockTypeBuilder
          .builder(BlockTripWireBehavior.class)
          .vanillaBlock(VanillaBlockId.TRIP_WIRE)
          .setProperties(VanillaBlockPropertyTypes.ATTACHED_BIT, VanillaBlockPropertyTypes.DISARMED_BIT, VanillaBlockPropertyTypes.POWERED_BIT, VanillaBlockPropertyTypes.SUSPENDED_BIT)
          .build();
}
