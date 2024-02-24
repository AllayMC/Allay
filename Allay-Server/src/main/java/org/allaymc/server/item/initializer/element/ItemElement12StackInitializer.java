package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement12Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement12StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_12_TYPE = ItemTypeBuilder
            .builder(ItemElement12Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_12)
            .build();
  }
}
