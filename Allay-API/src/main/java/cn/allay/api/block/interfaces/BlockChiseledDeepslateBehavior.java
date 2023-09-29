package cn.allay.api.block.interfaces;

import cn.allay.api.block.BlockBehavior;
import cn.allay.api.block.type.BlockType;
import cn.allay.api.block.type.BlockTypeBuilder;
import cn.allay.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockChiseledDeepslateBehavior extends BlockBehavior {
  BlockType<BlockChiseledDeepslateBehavior> CHISELED_DEEPSLATE_TYPE = BlockTypeBuilder
          .builder(BlockChiseledDeepslateBehavior.class)
          .vanillaBlock(VanillaBlockId.CHISELED_DEEPSLATE)
          .build();
}
