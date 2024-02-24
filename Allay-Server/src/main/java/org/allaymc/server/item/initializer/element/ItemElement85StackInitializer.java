package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement85Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement85StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_85_TYPE = ItemTypeBuilder
            .builder(ItemElement85Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_85)
            .build();
  }
}
