package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement5Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement5StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_5_TYPE = ItemTypeBuilder
            .builder(ItemElement5Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_5)
            .build();
  }
}
