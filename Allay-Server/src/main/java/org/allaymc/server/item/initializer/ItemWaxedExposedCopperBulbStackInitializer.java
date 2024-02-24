package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemWaxedExposedCopperBulbStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaxedExposedCopperBulbStackInitializer {
  static void init() {
    ItemTypes.WAXED_EXPOSED_COPPER_BULB_TYPE = ItemTypeBuilder
            .builder(ItemWaxedExposedCopperBulbStack.class)
            .vanillaItem(VanillaItemId.WAXED_EXPOSED_COPPER_BULB)
            .build();
  }
}
