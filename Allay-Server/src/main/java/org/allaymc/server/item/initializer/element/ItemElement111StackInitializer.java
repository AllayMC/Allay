package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement111Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement111StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_111_TYPE = ItemTypeBuilder
            .builder(ItemElement111Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_111)
            .build();
  }
}
