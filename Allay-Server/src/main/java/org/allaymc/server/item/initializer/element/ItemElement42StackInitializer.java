package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement42Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement42StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_42_TYPE = ItemTypeBuilder
            .builder(ItemElement42Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_42)
            .build();
  }
}
