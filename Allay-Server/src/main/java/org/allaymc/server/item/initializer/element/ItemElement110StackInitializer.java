package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement110Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement110StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_110_TYPE = ItemTypeBuilder
            .builder(ItemElement110Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_110)
            .build();
  }
}
