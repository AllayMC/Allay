package cn.allay.api.block.interfaces.element;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement19Behavior extends BlockBehavior {
  BlockType<BlockElement19Behavior> ELEMENT_19_TYPE = BlockTypeBuilder
          .builder(BlockElement19Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_19)
          .build();
}
