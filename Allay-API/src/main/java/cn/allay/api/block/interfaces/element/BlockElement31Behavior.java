package cn.allay.api.block.interfaces.element;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement31Behavior extends BlockBehavior {
  BlockType<BlockElement31Behavior> ELEMENT_31_TYPE = BlockTypeBuilder
          .builder(BlockElement31Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_31)
          .build();
}
