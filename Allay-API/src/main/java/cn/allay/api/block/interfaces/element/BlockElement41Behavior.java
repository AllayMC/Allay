package cn.allay.api.block.interfaces.element;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement41Behavior extends BlockBehavior {
  BlockType<BlockElement41Behavior> ELEMENT_41_TYPE = BlockTypeBuilder
          .builder(BlockElement41Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_41)
          .build();
}
