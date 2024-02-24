package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement32Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement32StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_32_TYPE = ItemTypeBuilder
            .builder(ItemElement32Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_32)
            .build();
  }
}
