package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemChorusFruitStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemChorusFruitStackInitializer {
  static void init() {
    ItemTypes.CHORUS_FRUIT_TYPE = ItemTypeBuilder
            .builder(ItemChorusFruitStack.class)
            .vanillaItem(VanillaItemId.CHORUS_FRUIT)
            .build();
  }
}
