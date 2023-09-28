package cn.allay.api.block.interfaces.element;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement46Behavior extends BlockBehavior {
  BlockType<BlockElement46Behavior> ELEMENT_46_TYPE = BlockTypeBuilder
          .builder(BlockElement46Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_46)
          .build();
}
