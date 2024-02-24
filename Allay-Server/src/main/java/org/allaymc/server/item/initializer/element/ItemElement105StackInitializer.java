package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement105Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement105StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_105_TYPE = ItemTypeBuilder
            .builder(ItemElement105Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_105)
            .build();
  }
}
