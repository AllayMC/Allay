package cn.allay.api.block.interfaces.deepslatecopperore;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockDeepslateCopperOreBehavior extends BlockBehavior {
  BlockType<BlockDeepslateCopperOreBehavior> DEEPSLATE_COPPER_ORE_TYPE = BlockTypeBuilder
          .builder(BlockDeepslateCopperOreBehavior.class)
          .vanillaBlock(VanillaBlockId.DEEPSLATE_COPPER_ORE)
          .build();
}
