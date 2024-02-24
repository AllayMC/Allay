package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement51Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement51StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_51_TYPE = ItemTypeBuilder
            .builder(ItemElement51Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_51)
            .build();
  }
}
