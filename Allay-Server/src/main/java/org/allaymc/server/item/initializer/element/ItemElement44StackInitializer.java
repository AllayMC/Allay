package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement44Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement44StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_44_TYPE = ItemTypeBuilder
            .builder(ItemElement44Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_44)
            .build();
  }
}
