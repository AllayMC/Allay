package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemDeadbushStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDeadbushStackInitializer {
  static void init() {
    ItemTypes.DEADBUSH_TYPE = ItemTypeBuilder
            .builder(ItemDeadbushStack.class)
            .vanillaItem(VanillaItemId.DEADBUSH)
            .build();
  }
}
