package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemDoublePlantStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDoublePlantStackInitializer {
  static void init() {
    ItemTypes.DOUBLE_PLANT_TYPE = ItemTypeBuilder
            .builder(ItemDoublePlantStack.class)
            .vanillaItem(VanillaItemId.DOUBLE_PLANT)
            .build();
  }
}
