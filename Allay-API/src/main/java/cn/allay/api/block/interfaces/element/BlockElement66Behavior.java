package cn.allay.api.block.interfaces.element;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement66Behavior extends BlockBehavior {
  BlockType<BlockElement66Behavior> ELEMENT_66_TYPE = BlockTypeBuilder
          .builder(BlockElement66Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_66)
          .build();
}
