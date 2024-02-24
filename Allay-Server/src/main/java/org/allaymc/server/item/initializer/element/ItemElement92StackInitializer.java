package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement92Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement92StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_92_TYPE = ItemTypeBuilder
            .builder(ItemElement92Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_92)
            .build();
  }
}
