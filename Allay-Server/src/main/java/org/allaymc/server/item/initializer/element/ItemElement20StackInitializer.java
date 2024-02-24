package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement20Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement20StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_20_TYPE = ItemTypeBuilder
            .builder(ItemElement20Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_20)
            .build();
  }
}
