package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement109Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement109StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_109_TYPE = ItemTypeBuilder
            .builder(ItemElement109Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_109)
            .build();
  }
}
