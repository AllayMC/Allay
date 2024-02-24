package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement33Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement33StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_33_TYPE = ItemTypeBuilder
            .builder(ItemElement33Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_33)
            .build();
  }
}
