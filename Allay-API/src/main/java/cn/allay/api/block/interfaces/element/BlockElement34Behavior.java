package cn.allay.api.block.interfaces.element;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement34Behavior extends BlockBehavior {
  BlockType<BlockElement34Behavior> ELEMENT_34_TYPE = BlockTypeBuilder
          .builder(BlockElement34Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_34)
          .build();
}
