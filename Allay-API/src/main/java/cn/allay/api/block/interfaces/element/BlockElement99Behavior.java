package cn.allay.api.block.interfaces.element;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement99Behavior extends BlockBehavior {
  BlockType<BlockElement99Behavior> ELEMENT_99_TYPE = BlockTypeBuilder
          .builder(BlockElement99Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_99)
          .build();
}
