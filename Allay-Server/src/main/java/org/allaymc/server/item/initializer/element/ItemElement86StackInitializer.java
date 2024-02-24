package org.allaymc.server.item.initializer.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.element.ItemElement86Stack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement86StackInitializer {
  static void init() {
    ItemTypes.ELEMENT_86_TYPE = ItemTypeBuilder
            .builder(ItemElement86Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_86)
            .build();
  }
}
