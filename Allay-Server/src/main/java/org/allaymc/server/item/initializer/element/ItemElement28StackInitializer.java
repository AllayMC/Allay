package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement28Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement28StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_28_TYPE = ItemTypeBuilder
            .builder(ItemElement28Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_28)
            .build();
  }
}
