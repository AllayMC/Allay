package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement3Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement3StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_3_TYPE = ItemTypeBuilder
            .builder(ItemElement3Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_3)
            .build();
  }
}
