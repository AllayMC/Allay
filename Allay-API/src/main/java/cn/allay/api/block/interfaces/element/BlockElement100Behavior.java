package cn.allay.api.block.interfaces.element;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement100Behavior extends BlockBehavior {
  BlockType<BlockElement100Behavior> ELEMENT_100_TYPE = BlockTypeBuilder
          .builder(BlockElement100Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_100)
          .build();
}
