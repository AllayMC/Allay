package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement39Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement39StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_39_TYPE = ItemTypeBuilder
            .builder(ItemElement39Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_39)
            .build();
  }
}
