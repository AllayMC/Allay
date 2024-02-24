package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement76Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement76StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_76_TYPE = ItemTypeBuilder
            .builder(ItemElement76Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_76)
            .build();
  }
}
