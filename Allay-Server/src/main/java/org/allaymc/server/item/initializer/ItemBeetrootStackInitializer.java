package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemBeetrootStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBeetrootStackInitializer {
  static void init() {
    ItemTypes.BEETROOT_TYPE = ItemTypeBuilder
            .builder(ItemBeetrootStack.class)
            .vanillaItem(VanillaItemId.BEETROOT)
            .build();
  }
}
