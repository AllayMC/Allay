package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement50Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement50StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_50_TYPE = ItemTypeBuilder
            .builder(ItemElement50Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_50)
            .build();
  }
}
