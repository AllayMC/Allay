package org.allaymc.server.item.initializer.trapdoor;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.trapdoor.ItemCopperTrapdoorStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCopperTrapdoorStackInitializer {
  static void init() {
    ItemTypes.COPPER_TRAPDOOR_TYPE = ItemTypeBuilder
            .builder(ItemCopperTrapdoorStack.class)
            .vanillaItem(VanillaItemId.COPPER_TRAPDOOR)
            .build();
  }
}
