package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement22Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement22StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_22_TYPE = ItemTypeBuilder
            .builder(ItemElement22Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_22)
            .build();
  }
}
