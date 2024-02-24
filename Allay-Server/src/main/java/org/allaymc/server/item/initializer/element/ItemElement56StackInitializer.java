package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement56Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement56StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_56_TYPE = ItemTypeBuilder
            .builder(ItemElement56Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_56)
            .build();
  }
}
