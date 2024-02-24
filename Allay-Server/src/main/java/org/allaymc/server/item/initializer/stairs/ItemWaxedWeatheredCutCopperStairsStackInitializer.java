package org.allaymc.server.item.initializer.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stairs.ItemWaxedWeatheredCutCopperStairsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaxedWeatheredCutCopperStairsStackInitializer {
  static void init() {
    ItemTypes.WAXED_WEATHERED_CUT_COPPER_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemWaxedWeatheredCutCopperStairsStack.class)
            .vanillaItem(VanillaItemId.WAXED_WEATHERED_CUT_COPPER_STAIRS)
            .build();
  }
}
