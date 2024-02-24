package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement29Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement29StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_29_TYPE = ItemTypeBuilder
            .builder(ItemElement29Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_29)
            .build();
  }
}
