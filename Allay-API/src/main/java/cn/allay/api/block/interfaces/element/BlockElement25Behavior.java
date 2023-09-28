package cn.allay.api.block.interfaces.element;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement25Behavior extends BlockBehavior {
  BlockType<BlockElement25Behavior> ELEMENT_25_TYPE = BlockTypeBuilder
          .builder(BlockElement25Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_25)
          .build();
}
