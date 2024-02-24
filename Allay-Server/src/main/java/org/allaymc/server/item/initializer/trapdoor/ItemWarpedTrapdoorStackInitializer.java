package org.allaymc.server.item.initializer.trapdoor;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.trapdoor.ItemWarpedTrapdoorStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWarpedTrapdoorStackInitializer {
  static void init() {
    ItemTypes.WARPED_TRAPDOOR_TYPE = ItemTypeBuilder
            .builder(ItemWarpedTrapdoorStack.class)
            .vanillaItem(VanillaItemId.WARPED_TRAPDOOR)
            .build();
  }
}
