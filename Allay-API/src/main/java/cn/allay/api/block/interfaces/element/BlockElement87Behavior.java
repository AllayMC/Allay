package cn.allay.api.block.interfaces.element;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement87Behavior extends BlockBehavior {
  BlockType<BlockElement87Behavior> ELEMENT_87_TYPE = BlockTypeBuilder
          .builder(BlockElement87Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_87)
          .build();
}
