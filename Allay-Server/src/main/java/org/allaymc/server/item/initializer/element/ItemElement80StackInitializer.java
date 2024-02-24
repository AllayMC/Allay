package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement80Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement80StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_80_TYPE = ItemTypeBuilder
            .builder(ItemElement80Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_80)
            .build();
  }
}
