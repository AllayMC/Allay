package cn.allay.api.block.interfaces.element;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement21Behavior extends BlockBehavior {
  BlockType<BlockElement21Behavior> ELEMENT_21_TYPE = BlockTypeBuilder
          .builder(BlockElement21Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_21)
          .build();
}
