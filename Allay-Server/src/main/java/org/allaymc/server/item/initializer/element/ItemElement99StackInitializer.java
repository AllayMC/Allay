package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement99Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement99StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_99_TYPE = ItemTypeBuilder
            .builder(ItemElement99Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_99)
            .build();
  }
}
