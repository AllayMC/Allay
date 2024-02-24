package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement69Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement69StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_69_TYPE = ItemTypeBuilder
            .builder(ItemElement69Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_69)
            .build();
  }
}
