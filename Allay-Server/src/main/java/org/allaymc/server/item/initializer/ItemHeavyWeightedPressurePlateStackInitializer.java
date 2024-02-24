package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemHeavyWeightedPressurePlateStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHeavyWeightedPressurePlateStackInitializer {
  static void init() {
    ItemTypes.HEAVY_WEIGHTED_PRESSURE_PLATE_TYPE = ItemTypeBuilder
            .builder(ItemHeavyWeightedPressurePlateStack.class)
            .vanillaItem(VanillaItemId.HEAVY_WEIGHTED_PRESSURE_PLATE)
            .build();
  }
}
