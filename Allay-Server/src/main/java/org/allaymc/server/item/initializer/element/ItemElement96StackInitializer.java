package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement96Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement96StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_96_TYPE = ItemTypeBuilder
            .builder(ItemElement96Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_96)
            .build();
  }
}
