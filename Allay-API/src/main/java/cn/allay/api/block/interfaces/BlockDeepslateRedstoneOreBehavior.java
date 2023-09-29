package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockDeepslateRedstoneOreBehavior extends BlockBehavior {
  BlockType<BlockDeepslateRedstoneOreBehavior> DEEPSLATE_REDSTONE_ORE_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateRedstoneOreBehavior.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_REDSTONE_ORE)
          .build();
}
