package cn.allay.api.block.interfaces.element;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement39Behavior extends BlockBehavior {
  BlockType<BlockElement39Behavior> ELEMENT_39_TYPE = BlockTypeBuilder
          .builder(BlockElement39Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_39)
          .build();
}
