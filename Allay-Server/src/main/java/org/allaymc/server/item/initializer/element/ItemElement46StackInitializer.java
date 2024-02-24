package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement46Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement46StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_46_TYPE = ItemTypeBuilder
            .builder(ItemElement46Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_46)
            .build();
  }
}
