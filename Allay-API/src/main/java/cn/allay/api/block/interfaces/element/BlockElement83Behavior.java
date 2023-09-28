package cn.allay.api.block.interfaces.element;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement83Behavior extends BlockBehavior {
  BlockType<BlockElement83Behavior> ELEMENT_83_TYPE = BlockTypeBuilder
          .builder(BlockElement83Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_83)
          .build();
}
