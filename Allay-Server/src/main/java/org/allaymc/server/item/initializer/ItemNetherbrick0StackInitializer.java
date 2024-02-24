package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemNetherbrick0Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemNetherbrick0StackInitializer {
  static void init() {
    ItemTypes.NETHERBRICK_TYPE = ItemTypeBuilder
            .builder(ItemNetherbrick0Stack.class)
            .vanillaItem(VanillaItemId.NETHERBRICK)
            .build();
  }
}
