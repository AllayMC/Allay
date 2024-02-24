package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement87Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement87StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_87_TYPE = ItemTypeBuilder
            .builder(ItemElement87Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_87)
            .build();
  }
}
