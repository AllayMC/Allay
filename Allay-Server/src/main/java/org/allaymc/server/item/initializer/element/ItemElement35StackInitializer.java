package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement35Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement35StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_35_TYPE = ItemTypeBuilder
            .builder(ItemElement35Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_35)
            .build();
  }
}
