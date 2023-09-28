package cn.allay.api.block.interfaces.copper;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockOxidizedCutCopperBehavior extends BlockBehavior {
  BlockType<BlockOxidizedCutCopperBehavior> OXIDIZED_CUT_COPPER_TYPE = BlockTypeBuilder
          .builder(BlockOxidizedCutCopperBehavior.class)
          .vanillaBlock(VanillaBlockId.OXIDIZED_CUT_COPPER)
          .build();
}
