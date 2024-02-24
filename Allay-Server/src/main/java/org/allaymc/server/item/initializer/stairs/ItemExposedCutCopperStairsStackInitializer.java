package org.allaymc.server.item.initializer.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stairs.ItemExposedCutCopperStairsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemExposedCutCopperStairsStackInitializer {
  static void init() {
    ItemTypes.EXPOSED_CUT_COPPER_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemExposedCutCopperStairsStack.class)
            .vanillaItem(VanillaItemId.EXPOSED_CUT_COPPER_STAIRS)
            .build();
  }
}
