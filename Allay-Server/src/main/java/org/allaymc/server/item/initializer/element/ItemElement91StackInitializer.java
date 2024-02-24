package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement91Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement91StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_91_TYPE = ItemTypeBuilder
            .builder(ItemElement91Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_91)
            .build();
  }
}
