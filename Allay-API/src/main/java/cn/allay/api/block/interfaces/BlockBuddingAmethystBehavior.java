package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockBuddingAmethystBehavior extends BlockBehavior {
  BlockType<BlockBuddingAmethystBehavior> BUDDING_AMETHYST_TYPE = BlockTypeBuilder
          .builder(BlockBuddingAmethystBehavior.class)
          .vanillaBlock(VanillaBlockId.BUDDING_AMETHYST)
          .build();
}
