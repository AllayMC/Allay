package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemBigDripleafStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBigDripleafStackInitializer {
  static void init() {
    ItemTypes.BIG_DRIPLEAF_TYPE = ItemTypeBuilder
            .builder(ItemBigDripleafStack.class)
            .vanillaItem(VanillaItemId.BIG_DRIPLEAF)
            .build();
  }
}
