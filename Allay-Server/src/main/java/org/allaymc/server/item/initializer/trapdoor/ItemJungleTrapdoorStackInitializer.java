package org.allaymc.server.item.initializer.trapdoor;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.trapdoor.ItemJungleTrapdoorStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemJungleTrapdoorStackInitializer {
  static void init() {
    ItemTypes.JUNGLE_TRAPDOOR_TYPE = ItemTypeBuilder
            .builder(ItemJungleTrapdoorStack.class)
            .vanillaItem(VanillaItemId.JUNGLE_TRAPDOOR)
            .build();
  }
}
