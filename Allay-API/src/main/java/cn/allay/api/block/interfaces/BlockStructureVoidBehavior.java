package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockStructureVoidBehavior extends BlockBehavior {
  BlockType<BlockStructureVoidBehavior> STRUCTURE_VOID_TYPE = BlockTypeBuilder
          .builder(BlockStructureVoidBehavior.class)
          .vanillaBlock(VanillaBlockId.STRUCTURE_VOID)
          .setProperties(VanillaBlockPropertyTypes.STRUCTURE_VOID_TYPE)
          .build();
}
