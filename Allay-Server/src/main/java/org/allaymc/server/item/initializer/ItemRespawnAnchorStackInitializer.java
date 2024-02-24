package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemRespawnAnchorStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRespawnAnchorStackInitializer {
  static void init() {
    ItemTypes.RESPAWN_ANCHOR_TYPE = ItemTypeBuilder
            .builder(ItemRespawnAnchorStack.class)
            .vanillaItem(VanillaItemId.RESPAWN_ANCHOR)
            .build();
  }
}
