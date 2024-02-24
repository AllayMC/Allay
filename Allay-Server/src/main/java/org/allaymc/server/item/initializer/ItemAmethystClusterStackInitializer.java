package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemAmethystClusterStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAmethystClusterStackInitializer {
  static void init() {
    ItemTypes.AMETHYST_CLUSTER_TYPE = ItemTypeBuilder
            .builder(ItemAmethystClusterStack.class)
            .vanillaItem(VanillaItemId.AMETHYST_CLUSTER)
            .build();
  }
}
