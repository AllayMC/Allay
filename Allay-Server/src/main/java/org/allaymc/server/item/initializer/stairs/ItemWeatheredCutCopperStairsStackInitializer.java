package org.allaymc.server.item.initializer.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stairs.ItemWeatheredCutCopperStairsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWeatheredCutCopperStairsStackInitializer {
  static void init() {
    ItemTypes.WEATHERED_CUT_COPPER_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemWeatheredCutCopperStairsStack.class)
            .vanillaItem(VanillaItemId.WEATHERED_CUT_COPPER_STAIRS)
            .build();
  }
}
