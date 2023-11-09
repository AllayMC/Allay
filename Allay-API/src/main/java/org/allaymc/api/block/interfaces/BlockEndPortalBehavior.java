package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockEndPortalBehavior extends BlockBehavior {
  BlockType<BlockEndPortalBehavior> END_PORTAL_TYPE = BlockTypeBuilder
          .builder(BlockEndPortalBehavior.class)
          .vanillaBlock(VanillaBlockId.END_PORTAL)
          .build();
}
