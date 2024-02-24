package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemChorusPlantStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemChorusPlantStackInitializer {
  static void init() {
    ItemTypes.CHORUS_PLANT_TYPE = ItemTypeBuilder
            .builder(ItemChorusPlantStack.class)
            .vanillaItem(VanillaItemId.CHORUS_PLANT)
            .build();
  }
}
