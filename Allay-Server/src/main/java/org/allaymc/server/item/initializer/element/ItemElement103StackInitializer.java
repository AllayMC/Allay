package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement103Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement103StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_103_TYPE = ItemTypeBuilder
            .builder(ItemElement103Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_103)
            .build();
  }
}
