package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement53Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement53StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_53_TYPE = ItemTypeBuilder
            .builder(ItemElement53Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_53)
            .build();
  }
}
