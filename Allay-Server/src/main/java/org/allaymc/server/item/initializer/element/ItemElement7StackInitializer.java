package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement7Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement7StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_7_TYPE = ItemTypeBuilder
            .builder(ItemElement7Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_7)
            .build();
  }
}
