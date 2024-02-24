package org.allaymc.server.item.initializer.copper;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.copper.ItemWaxedOxidizedCutCopperStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaxedOxidizedCutCopperStackInitializer {
  static void init() {
    ItemTypes.WAXED_OXIDIZED_CUT_COPPER_TYPE = ItemTypeBuilder
            .builder(ItemWaxedOxidizedCutCopperStack.class)
            .vanillaItem(VanillaItemId.WAXED_OXIDIZED_CUT_COPPER)
            .build();
  }
}
