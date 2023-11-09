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
public interface BlockEndPortalFrameBehavior extends BlockBehavior {
  BlockType<BlockEndPortalFrameBehavior> END_PORTAL_FRAME_TYPE = BlockTypeBuilder
          .builder(BlockEndPortalFrameBehavior.class)
          .vanillaBlock(VanillaBlockId.END_PORTAL_FRAME)
          .setProperties(VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION, VanillaBlockPropertyTypes.END_PORTAL_EYE_BIT)
          .build();
}
