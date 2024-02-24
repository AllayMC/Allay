package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement59Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement59StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_59_TYPE = ItemTypeBuilder
            .builder(ItemElement59Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_59)
            .build();
  }
}
