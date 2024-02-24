package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemDragonEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDragonEggStackInitializer {
  static void init() {
    ItemTypes.DRAGON_EGG_TYPE = ItemTypeBuilder
            .builder(ItemDragonEggStack.class)
            .vanillaItem(VanillaItemId.DRAGON_EGG)
            .build();
  }
}
