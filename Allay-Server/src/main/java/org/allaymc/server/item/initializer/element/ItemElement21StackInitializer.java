package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement21Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement21StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_21_TYPE = ItemTypeBuilder
            .builder(ItemElement21Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_21)
            .build();
  }
}
