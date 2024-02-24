package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemLightWeightedPressurePlateStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLightWeightedPressurePlateStackInitializer {
  static void init() {
    ItemTypes.LIGHT_WEIGHTED_PRESSURE_PLATE_TYPE = ItemTypeBuilder
            .builder(ItemLightWeightedPressurePlateStack.class)
            .vanillaItem(VanillaItemId.LIGHT_WEIGHTED_PRESSURE_PLATE)
            .build();
  }
}
