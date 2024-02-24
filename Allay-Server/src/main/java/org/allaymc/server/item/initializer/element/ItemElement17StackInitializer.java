package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement17Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement17StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_17_TYPE = ItemTypeBuilder
            .builder(ItemElement17Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_17)
            .build();
  }
}
