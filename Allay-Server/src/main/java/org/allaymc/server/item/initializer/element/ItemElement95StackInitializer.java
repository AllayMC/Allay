package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement95Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement95StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_95_TYPE = ItemTypeBuilder
            .builder(ItemElement95Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_95)
            .build();
  }
}
