package org.allaymc.server.item.initializer.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stairs.ItemWaxedCutCopperStairsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaxedCutCopperStairsStackInitializer {
  static void init() {
    ItemTypes.WAXED_CUT_COPPER_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemWaxedCutCopperStairsStack.class)
            .vanillaItem(VanillaItemId.WAXED_CUT_COPPER_STAIRS)
            .build();
  }
}
