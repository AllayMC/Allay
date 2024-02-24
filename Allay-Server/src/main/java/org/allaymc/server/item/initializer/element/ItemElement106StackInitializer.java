package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement106Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement106StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_106_TYPE = ItemTypeBuilder
            .builder(ItemElement106Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_106)
            .build();
  }
}
