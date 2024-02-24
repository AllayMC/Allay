package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemDripstoneBlockStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDripstoneBlockStackInitializer {
  static void init() {
    ItemTypes.DRIPSTONE_BLOCK_TYPE = ItemTypeBuilder
            .builder(ItemDripstoneBlockStack.class)
            .vanillaItem(VanillaItemId.DRIPSTONE_BLOCK)
            .build();
  }
}
