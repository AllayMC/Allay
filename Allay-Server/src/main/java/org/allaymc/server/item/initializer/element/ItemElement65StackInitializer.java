package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement65Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement65StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_65_TYPE = ItemTypeBuilder
            .builder(ItemElement65Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_65)
            .build();
  }
}
