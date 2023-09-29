package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockDeepslateEmeraldOreBehavior extends BlockBehavior {
  BlockType<BlockDeepslateEmeraldOreBehavior> DEEPSLATE_EMERALD_ORE_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateEmeraldOreBehavior.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_EMERALD_ORE)
          .build();
}
