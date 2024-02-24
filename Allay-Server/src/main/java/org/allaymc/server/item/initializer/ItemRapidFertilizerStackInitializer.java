package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemRapidFertilizerStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRapidFertilizerStackInitializer {
  static void init() {
    ItemTypes.RAPID_FERTILIZER_TYPE = ItemTypeBuilder
            .builder(ItemRapidFertilizerStack.class)
            .vanillaItem(VanillaItemId.RAPID_FERTILIZER)
            .build();
  }
}
