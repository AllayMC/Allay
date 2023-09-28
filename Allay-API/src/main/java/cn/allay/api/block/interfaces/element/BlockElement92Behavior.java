package cn.allay.api.block.interfaces.element;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement92Behavior extends BlockBehavior {
  BlockType<BlockElement92Behavior> ELEMENT_92_TYPE = BlockTypeBuilder
          .builder(BlockElement92Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_92)
          .build();
}
