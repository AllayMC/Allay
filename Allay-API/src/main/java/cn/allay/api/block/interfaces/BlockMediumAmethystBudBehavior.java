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
public interface BlockMediumAmethystBudBehavior extends BlockBehavior {
  BlockType<BlockMediumAmethystBudBehavior> MEDIUM_AMETHYST_BUD_TYPE = BlockTypeBuilder
          .builder(BlockMediumAmethystBudBehavior.class)
          .vanillaBlock(VanillaBlockId.MEDIUM_AMETHYST_BUD)
          .setProperties(VanillaBlockPropertyTypes.FACING_DIRECTION)
          .build();
}
