package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement18Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement18StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_18_TYPE = ItemTypeBuilder
            .builder(ItemElement18Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_18)
            .build();
  }
}
