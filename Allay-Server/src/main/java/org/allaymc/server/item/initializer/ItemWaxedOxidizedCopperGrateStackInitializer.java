package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemWaxedOxidizedCopperGrateStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaxedOxidizedCopperGrateStackInitializer {
  static void init() {
    ItemTypes.WAXED_OXIDIZED_COPPER_GRATE_TYPE = ItemTypeBuilder
            .builder(ItemWaxedOxidizedCopperGrateStack.class)
            .vanillaItem(VanillaItemId.WAXED_OXIDIZED_COPPER_GRATE)
            .build();
  }
}
