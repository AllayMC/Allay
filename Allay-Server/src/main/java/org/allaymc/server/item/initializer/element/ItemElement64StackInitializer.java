package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement64Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement64StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_64_TYPE = ItemTypeBuilder
            .builder(ItemElement64Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_64)
            .build();
  }
}
