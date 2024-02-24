package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement47Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement47StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_47_TYPE = ItemTypeBuilder
            .builder(ItemElement47Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_47)
            .build();
  }
}
