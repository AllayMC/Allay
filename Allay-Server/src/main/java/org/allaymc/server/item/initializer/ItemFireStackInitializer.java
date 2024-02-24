package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemFireStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemFireStackInitializer {
  static void init() {
    ItemTypes.FIRE_TYPE = ItemTypeBuilder
            .builder(ItemFireStack.class)
            .vanillaItem(VanillaItemId.FIRE)
            .build();
  }
}
