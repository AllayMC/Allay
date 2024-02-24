package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemFloweringAzaleaStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemFloweringAzaleaStackInitializer {
  static void init() {
    ItemTypes.FLOWERING_AZALEA_TYPE = ItemTypeBuilder
            .builder(ItemFloweringAzaleaStack.class)
            .vanillaItem(VanillaItemId.FLOWERING_AZALEA)
            .build();
  }
}
