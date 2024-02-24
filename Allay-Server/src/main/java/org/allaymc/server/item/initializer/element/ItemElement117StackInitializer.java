package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement117Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement117StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_117_TYPE = ItemTypeBuilder
            .builder(ItemElement117Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_117)
            .build();
  }
}
