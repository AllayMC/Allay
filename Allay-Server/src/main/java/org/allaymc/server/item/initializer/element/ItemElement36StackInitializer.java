package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement36Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement36StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_36_TYPE = ItemTypeBuilder
            .builder(ItemElement36Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_36)
            .build();
  }
}
