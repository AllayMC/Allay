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
public interface BlockAnvilBehavior extends BlockBehavior {
  BlockType<BlockAnvilBehavior> ANVIL_TYPE = BlockTypeBuilder
          .builder(BlockAnvilBehavior.class)
          .vanillaBlock(VanillaBlockId.ANVIL)
          .setProperties(VanillaBlockPropertyTypes.DAMAGE, VanillaBlockPropertyTypes.DIRECTION)
          .build();
}
