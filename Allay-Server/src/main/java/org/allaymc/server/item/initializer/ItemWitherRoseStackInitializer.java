package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemWitherRoseStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWitherRoseStackInitializer {
  static void init() {
    ItemTypes.WITHER_ROSE_TYPE = ItemTypeBuilder
            .builder(ItemWitherRoseStack.class)
            .vanillaItem(VanillaItemId.WITHER_ROSE)
            .build();
  }
}
