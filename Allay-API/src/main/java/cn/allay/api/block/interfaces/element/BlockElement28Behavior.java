package cn.allay.api.block.interfaces.element;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement28Behavior extends BlockBehavior {
  BlockType<BlockElement28Behavior> ELEMENT_28_TYPE = BlockTypeBuilder
          .builder(BlockElement28Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_28)
          .build();
}
