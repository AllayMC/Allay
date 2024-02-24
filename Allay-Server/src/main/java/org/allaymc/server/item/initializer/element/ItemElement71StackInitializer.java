package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement71Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement71StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_71_TYPE = ItemTypeBuilder
            .builder(ItemElement71Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_71)
            .build();
  }
}
