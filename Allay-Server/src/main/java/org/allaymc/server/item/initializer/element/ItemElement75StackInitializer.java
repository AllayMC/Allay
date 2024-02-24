package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement75Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement75StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_75_TYPE = ItemTypeBuilder
            .builder(ItemElement75Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_75)
            .build();
  }
}
