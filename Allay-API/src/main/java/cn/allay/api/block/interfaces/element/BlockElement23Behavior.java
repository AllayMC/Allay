package cn.allay.api.block.interfaces.element;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement23Behavior extends BlockBehavior {
  BlockType<BlockElement23Behavior> ELEMENT_23_TYPE = BlockTypeBuilder
          .builder(BlockElement23Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_23)
          .build();
}
