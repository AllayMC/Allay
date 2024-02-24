package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemPoppedChorusFruitStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPoppedChorusFruitStackInitializer {
  static void init() {
    ItemTypes.POPPED_CHORUS_FRUIT_TYPE = ItemTypeBuilder
            .builder(ItemPoppedChorusFruitStack.class)
            .vanillaItem(VanillaItemId.POPPED_CHORUS_FRUIT)
            .build();
  }
}
