package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemWebStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWebStackInitializer {
  static void init() {
    ItemTypes.WEB_TYPE = ItemTypeBuilder
            .builder(ItemWebStack.class)
            .vanillaItem(VanillaItemId.WEB)
            .build();
  }
}
