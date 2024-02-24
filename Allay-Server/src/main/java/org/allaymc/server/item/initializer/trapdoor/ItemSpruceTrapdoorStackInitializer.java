package org.allaymc.server.item.initializer.trapdoor;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.trapdoor.ItemSpruceTrapdoorStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSpruceTrapdoorStackInitializer {
  static void init() {
    ItemTypes.SPRUCE_TRAPDOOR_TYPE = ItemTypeBuilder
            .builder(ItemSpruceTrapdoorStack.class)
            .vanillaItem(VanillaItemId.SPRUCE_TRAPDOOR)
            .build();
  }
}
