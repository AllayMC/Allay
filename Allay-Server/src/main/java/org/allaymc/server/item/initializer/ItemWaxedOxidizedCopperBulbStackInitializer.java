package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemWaxedOxidizedCopperBulbStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaxedOxidizedCopperBulbStackInitializer {
  static void init() {
    ItemTypes.WAXED_OXIDIZED_COPPER_BULB_TYPE = ItemTypeBuilder
            .builder(ItemWaxedOxidizedCopperBulbStack.class)
            .vanillaItem(VanillaItemId.WAXED_OXIDIZED_COPPER_BULB)
            .build();
  }
}
