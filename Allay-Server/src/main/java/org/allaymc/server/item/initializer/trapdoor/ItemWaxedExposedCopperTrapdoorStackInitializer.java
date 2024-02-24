package org.allaymc.server.item.initializer.trapdoor;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.trapdoor.ItemWaxedExposedCopperTrapdoorStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaxedExposedCopperTrapdoorStackInitializer {
  static void init() {
    ItemTypes.WAXED_EXPOSED_COPPER_TRAPDOOR_TYPE = ItemTypeBuilder
            .builder(ItemWaxedExposedCopperTrapdoorStack.class)
            .vanillaItem(VanillaItemId.WAXED_EXPOSED_COPPER_TRAPDOOR)
            .build();
  }
}
