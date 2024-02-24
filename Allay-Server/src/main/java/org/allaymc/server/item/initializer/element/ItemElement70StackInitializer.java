package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement70Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement70StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_70_TYPE = ItemTypeBuilder
            .builder(ItemElement70Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_70)
            .build();
  }
}
