package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement41Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement41StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_41_TYPE = ItemTypeBuilder
            .builder(ItemElement41Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_41)
            .build();
  }
}
