package cn.allay.api.block.interfaces.element;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement74Behavior extends BlockBehavior {
  BlockType<BlockElement74Behavior> ELEMENT_74_TYPE = BlockTypeBuilder
          .builder(BlockElement74Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_74)
          .build();
}
