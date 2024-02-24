package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement83Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement83StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_83_TYPE = ItemTypeBuilder
            .builder(ItemElement83Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_83)
            .build();
  }
}
