package org.allaymc.server.item.initializer.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.stairs.ItemWaxedOxidizedCutCopperStairsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaxedOxidizedCutCopperStairsStackInitializer {
  static void init() {
    ItemTypes.WAXED_OXIDIZED_CUT_COPPER_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemWaxedOxidizedCutCopperStairsStack.class)
            .vanillaItem(VanillaItemId.WAXED_OXIDIZED_CUT_COPPER_STAIRS)
            .build();
  }
}
