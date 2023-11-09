package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockWebBehavior extends BlockBehavior {
  BlockType<BlockWebBehavior> WEB_TYPE = BlockTypeBuilder
          .builder(BlockWebBehavior.class)
          .vanillaBlock(VanillaBlockId.WEB)
          .build();
}
