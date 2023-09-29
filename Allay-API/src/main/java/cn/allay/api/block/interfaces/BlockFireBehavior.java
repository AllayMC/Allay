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
public interface BlockFireBehavior extends BlockBehavior {
  BlockType<BlockFireBehavior> FIRE_TYPE = BlockTypeBuilder
          .builder(BlockFireBehavior.class)
          .vanillaBlock(VanillaBlockId.FIRE)
          .setProperties(VanillaBlockPropertyTypes.AGE_16)
          .build();
}
