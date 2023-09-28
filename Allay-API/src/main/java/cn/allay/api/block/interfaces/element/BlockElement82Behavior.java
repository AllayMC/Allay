package cn.allay.api.block.interfaces.element;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement82Behavior extends BlockBehavior {
  BlockType<BlockElement82Behavior> ELEMENT_82_TYPE = BlockTypeBuilder
          .builder(BlockElement82Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_82)
          .build();
}
