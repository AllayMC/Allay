package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockCopperOreBehavior extends BlockBehavior {
  BlockType<BlockCopperOreBehavior> COPPER_ORE_TYPE = BlockTypeBuilder
          .builder(BlockCopperOreBehavior.class)
          .vanillaBlock(VanillaBlockId.COPPER_ORE)
          .build();
}
