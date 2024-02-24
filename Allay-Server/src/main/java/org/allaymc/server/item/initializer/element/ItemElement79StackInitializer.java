package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement79Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement79StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_79_TYPE = ItemTypeBuilder
            .builder(ItemElement79Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_79)
            .build();
  }
}
