package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement78Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement78StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_78_TYPE = ItemTypeBuilder
            .builder(ItemElement78Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_78)
            .build();
  }
}
