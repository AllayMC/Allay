package cn.allay.api.block.interfaces.element;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement38Behavior extends BlockBehavior {
  BlockType<BlockElement38Behavior> ELEMENT_38_TYPE = BlockTypeBuilder
          .builder(BlockElement38Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_38)
          .build();
}
