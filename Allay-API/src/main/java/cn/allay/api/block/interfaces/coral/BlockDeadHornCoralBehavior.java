package cn.allay.api.block.interfaces.coral;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockDeadHornCoralBehavior extends BlockBehavior {
  BlockType<BlockDeadHornCoralBehavior> DEAD_HORN_CORAL_TYPE = BlockTypeBuilder
          .builder(BlockDeadHornCoralBehavior.class)
          .vanillaBlock(VanillaBlockId.DEAD_HORN_CORAL)
          .build();
}
