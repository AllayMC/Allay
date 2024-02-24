package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemExposedCopperBulbStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemExposedCopperBulbStackInitializer {
  static void init() {
    ItemTypes.EXPOSED_COPPER_BULB_TYPE = ItemTypeBuilder
            .builder(ItemExposedCopperBulbStack.class)
            .vanillaItem(VanillaItemId.EXPOSED_COPPER_BULB)
            .build();
  }
}
