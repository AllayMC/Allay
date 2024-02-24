package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemWaxedExposedCopperGrateStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaxedExposedCopperGrateStackInitializer {
  static void init() {
    ItemTypes.WAXED_EXPOSED_COPPER_GRATE_TYPE = ItemTypeBuilder
            .builder(ItemWaxedExposedCopperGrateStack.class)
            .vanillaItem(VanillaItemId.WAXED_EXPOSED_COPPER_GRATE)
            .build();
  }
}
