package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement58Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement58StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_58_TYPE = ItemTypeBuilder
            .builder(ItemElement58Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_58)
            .build();
  }
}
