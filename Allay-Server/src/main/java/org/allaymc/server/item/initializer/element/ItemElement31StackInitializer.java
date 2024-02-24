package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement31Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement31StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_31_TYPE = ItemTypeBuilder
            .builder(ItemElement31Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_31)
            .build();
  }
}
