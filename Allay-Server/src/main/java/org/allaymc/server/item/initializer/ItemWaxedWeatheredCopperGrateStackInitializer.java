package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemWaxedWeatheredCopperGrateStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaxedWeatheredCopperGrateStackInitializer {
  static void init() {
    ItemTypes.WAXED_WEATHERED_COPPER_GRATE_TYPE = ItemTypeBuilder
            .builder(ItemWaxedWeatheredCopperGrateStack.class)
            .vanillaItem(VanillaItemId.WAXED_WEATHERED_COPPER_GRATE)
            .build();
  }
}
