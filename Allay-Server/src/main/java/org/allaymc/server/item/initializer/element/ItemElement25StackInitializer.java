package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement25Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement25StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_25_TYPE = ItemTypeBuilder
            .builder(ItemElement25Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_25)
            .build();
  }
}
