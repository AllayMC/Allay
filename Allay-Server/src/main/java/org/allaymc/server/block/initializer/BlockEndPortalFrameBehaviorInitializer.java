package org.allaymc.server.block.initializer;

import org.allaymc.api.block.interfaces.BlockEndPortalFrameBehavior;
import org.allaymc.api.block.type.BlockTypeBuilder;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.api.data.VanillaBlockPropertyTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface BlockEndPortalFrameBehaviorInitializer {
  static void init() {
    BlockTypes.END_PORTAL_FRAME_TYPE = BlockTypeBuilder
            .builder(BlockEndPortalFrameBehavior.class)
            .vanillaBlock(VanillaBlockId.END_PORTAL_FRAME)
            .setProperties(VanillaBlockPropertyTypes.END_PORTAL_EYE_BIT, VanillaBlockPropertyTypes.MINECRAFT_CARDINAL_DIRECTION)
            .build();
  }
}
