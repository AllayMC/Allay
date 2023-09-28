package cn.allay.api.block.interfaces.element;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement59Behavior extends BlockBehavior {
  BlockType<BlockElement59Behavior> ELEMENT_59_TYPE = BlockTypeBuilder
          .builder(BlockElement59Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_59)
          .build();
}
