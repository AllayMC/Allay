package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement68Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement68StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_68_TYPE = ItemTypeBuilder
            .builder(ItemElement68Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_68)
            .build();
  }
}
