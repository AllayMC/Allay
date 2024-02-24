package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement49Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement49StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_49_TYPE = ItemTypeBuilder
            .builder(ItemElement49Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_49)
            .build();
  }
}
