package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement0Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement0StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_0_TYPE = ItemTypeBuilder
            .builder(ItemElement0Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_0)
            .build();
  }
}
