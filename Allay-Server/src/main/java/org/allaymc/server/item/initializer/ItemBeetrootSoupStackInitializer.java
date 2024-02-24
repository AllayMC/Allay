package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemBeetrootSoupStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBeetrootSoupStackInitializer {
  static void init() {
    ItemTypes.BEETROOT_SOUP_TYPE = ItemTypeBuilder
            .builder(ItemBeetrootSoupStack.class)
            .vanillaItem(VanillaItemId.BEETROOT_SOUP)
            .build();
  }
}
