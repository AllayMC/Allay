package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement114Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement114StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_114_TYPE = ItemTypeBuilder
            .builder(ItemElement114Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_114)
            .build();
  }
}
