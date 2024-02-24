package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement6Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement6StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_6_TYPE = ItemTypeBuilder
            .builder(ItemElement6Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_6)
            .build();
  }
}
