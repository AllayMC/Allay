package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement52Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement52StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_52_TYPE = ItemTypeBuilder
            .builder(ItemElement52Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_52)
            .build();
  }
}
