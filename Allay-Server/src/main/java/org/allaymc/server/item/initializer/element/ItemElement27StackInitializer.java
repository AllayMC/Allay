package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement27Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement27StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_27_TYPE = ItemTypeBuilder
            .builder(ItemElement27Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_27)
            .build();
  }
}
