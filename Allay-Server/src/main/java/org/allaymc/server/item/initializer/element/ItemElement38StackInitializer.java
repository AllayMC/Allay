package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement38Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement38StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_38_TYPE = ItemTypeBuilder
            .builder(ItemElement38Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_38)
            .build();
  }
}
