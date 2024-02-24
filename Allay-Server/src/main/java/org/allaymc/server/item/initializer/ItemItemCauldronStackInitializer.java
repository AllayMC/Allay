package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemItemCauldronStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemCauldronStackInitializer {
  static void init() {
    ItemTypes.ITEM_CAULDRON_TYPE = ItemTypeBuilder
            .builder(ItemItemCauldronStack.class)
            .vanillaItem(VanillaItemId.ITEM_CAULDRON)
            .build();
  }
}
