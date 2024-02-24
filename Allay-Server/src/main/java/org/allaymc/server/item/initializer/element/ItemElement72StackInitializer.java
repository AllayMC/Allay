package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement72Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement72StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_72_TYPE = ItemTypeBuilder
            .builder(ItemElement72Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_72)
            .build();
  }
}
