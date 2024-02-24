package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement24Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement24StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_24_TYPE = ItemTypeBuilder
            .builder(ItemElement24Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_24)
            .build();
  }
}
