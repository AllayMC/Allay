package cn.allay.api.block.interfaces.element;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement79Behavior extends BlockBehavior {
  BlockType<BlockElement79Behavior> ELEMENT_79_TYPE = BlockTypeBuilder
          .builder(BlockElement79Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_79)
          .build();
}
