package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement93Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement93StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_93_TYPE = ItemTypeBuilder
            .builder(ItemElement93Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_93)
            .build();
  }
}
