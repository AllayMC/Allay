package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement60Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement60StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_60_TYPE = ItemTypeBuilder
            .builder(ItemElement60Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_60)
            .build();
  }
}
