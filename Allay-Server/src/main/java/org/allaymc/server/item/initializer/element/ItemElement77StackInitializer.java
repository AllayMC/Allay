package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement77Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement77StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_77_TYPE = ItemTypeBuilder
            .builder(ItemElement77Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_77)
            .build();
  }
}
