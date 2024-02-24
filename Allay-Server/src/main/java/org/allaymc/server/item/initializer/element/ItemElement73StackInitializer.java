package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement73Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement73StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_73_TYPE = ItemTypeBuilder
            .builder(ItemElement73Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_73)
            .build();
  }
}
