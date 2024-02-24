package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement23Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement23StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_23_TYPE = ItemTypeBuilder
            .builder(ItemElement23Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_23)
            .build();
  }
}
