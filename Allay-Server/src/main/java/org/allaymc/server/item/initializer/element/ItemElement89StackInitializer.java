package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement89Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement89StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_89_TYPE = ItemTypeBuilder
            .builder(ItemElement89Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_89)
            .build();
  }
}
