package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemSnifferEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSnifferEggStackInitializer {
  static void init() {
    ItemTypes.SNIFFER_EGG_TYPE = ItemTypeBuilder
            .builder(ItemSnifferEggStack.class)
            .vanillaItem(VanillaItemId.SNIFFER_EGG)
            .build();
  }
}
