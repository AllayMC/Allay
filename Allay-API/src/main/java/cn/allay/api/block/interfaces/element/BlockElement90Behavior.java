package cn.allay.api.block.interfaces.element;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement90Behavior extends BlockBehavior {
  BlockType<BlockElement90Behavior> ELEMENT_90_TYPE = BlockTypeBuilder
          .builder(BlockElement90Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_90)
          .build();
}
