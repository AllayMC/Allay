package org.allaymc.api.block.interfaces;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd | Cool_Loong <br>
 * Allay Project <br>
 */
public interface BlockPortalBehavior extends BlockBehavior {
  BlockType<BlockPortalBehavior> PORTAL_TYPE = BlockTypeBuilder
          .builder(BlockPortalBehavior.class)
          .vanillaBlock(VanillaBlockId.PORTAL)
          .setProperties(VanillaBlockPropertyTypes.PORTAL_AXIS)
          .build();
}
