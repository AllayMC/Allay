package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemBeefStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBeefStackInitializer {
  static void init() {
    ItemTypes.BEEF_TYPE = ItemTypeBuilder
            .builder(ItemBeefStack.class)
            .vanillaItem(VanillaItemId.BEEF)
            .build();
  }
}
