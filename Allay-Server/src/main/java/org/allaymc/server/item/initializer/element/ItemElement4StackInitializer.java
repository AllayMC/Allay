package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement4Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement4StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_4_TYPE = ItemTypeBuilder
            .builder(ItemElement4Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_4)
            .build();
  }
}
