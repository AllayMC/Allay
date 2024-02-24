package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement45Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement45StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_45_TYPE = ItemTypeBuilder
            .builder(ItemElement45Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_45)
            .build();
  }
}
