package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemEggStackInitializer {
  static void init() {
    ItemTypes.EGG_TYPE = ItemTypeBuilder
            .builder(ItemEggStack.class)
            .vanillaItem(VanillaItemId.EGG)
            .build();
  }
}
