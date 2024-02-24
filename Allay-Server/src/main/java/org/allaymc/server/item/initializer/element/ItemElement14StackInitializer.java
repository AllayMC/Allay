package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement14Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement14StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_14_TYPE = ItemTypeBuilder
            .builder(ItemElement14Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_14)
            .build();
  }
}
