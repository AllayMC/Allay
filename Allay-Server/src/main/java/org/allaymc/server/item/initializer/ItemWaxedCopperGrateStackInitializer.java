package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemWaxedCopperGrateStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaxedCopperGrateStackInitializer {
  static void init() {
    ItemTypes.WAXED_COPPER_GRATE_TYPE = ItemTypeBuilder
            .builder(ItemWaxedCopperGrateStack.class)
            .vanillaItem(VanillaItemId.WAXED_COPPER_GRATE)
            .build();
  }
}
