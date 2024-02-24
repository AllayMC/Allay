package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement81Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement81StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_81_TYPE = ItemTypeBuilder
            .builder(ItemElement81Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_81)
            .build();
  }
}
