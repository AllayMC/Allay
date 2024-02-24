package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement116Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement116StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_116_TYPE = ItemTypeBuilder
            .builder(ItemElement116Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_116)
            .build();
  }
}
