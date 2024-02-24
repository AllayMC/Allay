package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement48Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement48StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_48_TYPE = ItemTypeBuilder
            .builder(ItemElement48Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_48)
            .build();
  }
}
