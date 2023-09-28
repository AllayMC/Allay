package cn.allay.api.block.interfaces.element;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockElement67Behavior extends BlockBehavior {
  BlockType<BlockElement67Behavior> ELEMENT_67_TYPE = BlockTypeBuilder
          .builder(BlockElement67Behavior.class)
          .vanillaBlock(VanillaBlockId.ELEMENT_67)
          .build();
}
