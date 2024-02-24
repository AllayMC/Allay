package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement90Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement90StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_90_TYPE = ItemTypeBuilder
            .builder(ItemElement90Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_90)
            .build();
  }
}
