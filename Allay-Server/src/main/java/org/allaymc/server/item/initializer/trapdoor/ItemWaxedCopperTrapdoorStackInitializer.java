package org.allaymc.server.item.initializer.trapdoor;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.trapdoor.ItemWaxedCopperTrapdoorStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaxedCopperTrapdoorStackInitializer {
  static void init() {
    ItemTypes.WAXED_COPPER_TRAPDOOR_TYPE = ItemTypeBuilder
            .builder(ItemWaxedCopperTrapdoorStack.class)
            .vanillaItem(VanillaItemId.WAXED_COPPER_TRAPDOOR)
            .build();
  }
}
