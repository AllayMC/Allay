package cn.allay.api.block.interfaces.element;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement49Behavior extends BlockBehavior {
  BlockType<BlockElement49Behavior> ELEMENT_49_TYPE = BlockTypeBuilder
          .builder(BlockElement49Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_49)
          .build();
}
