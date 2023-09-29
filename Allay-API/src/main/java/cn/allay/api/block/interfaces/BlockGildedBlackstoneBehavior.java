package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockGildedBlackstoneBehavior extends BlockBehavior {
  BlockType<BlockGildedBlackstoneBehavior> GILDED_BLACKSTONE_TYPE = BlockTypeBuilder
          .builder(BlockGildedBlackstoneBehavior.class)
          .vanillaBlock(VanillaBlockId.GILDED_BLACKSTONE)
          .build();
}
