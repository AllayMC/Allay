package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement88Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement88StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_88_TYPE = ItemTypeBuilder
            .builder(ItemElement88Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_88)
            .build();
  }
}
