package cn.allay.api.block.interfaces.coral;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockBrainCoralBehavior extends BlockBehavior {
  BlockType<BlockBrainCoralBehavior> BRAIN_CORAL_TYPE = BlockTypeBuilder
          .builder(BlockBrainCoralBehavior.class)
          .vanillaBlock(VanillaBlockId.BRAIN_CORAL)
          .build();
}
