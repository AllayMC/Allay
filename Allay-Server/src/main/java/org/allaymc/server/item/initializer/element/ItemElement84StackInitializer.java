package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement84Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement84StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_84_TYPE = ItemTypeBuilder
            .builder(ItemElement84Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_84)
            .build();
  }
}
