package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement82Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement82StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_82_TYPE = ItemTypeBuilder
            .builder(ItemElement82Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_82)
            .build();
  }
}
