package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemArrowStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemArrowStackInitializer {
  static void init() {
    ItemTypes.ARROW_TYPE = ItemTypeBuilder
            .builder(ItemArrowStack.class)
            .vanillaItem(VanillaItemId.ARROW)
            .build();
  }
}
