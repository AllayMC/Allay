package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemSoulFireStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSoulFireStackInitializer {
  static void init() {
    ItemTypes.SOUL_FIRE_TYPE = ItemTypeBuilder
            .builder(ItemSoulFireStack.class)
            .vanillaItem(VanillaItemId.SOUL_FIRE)
            .build();
  }
}
