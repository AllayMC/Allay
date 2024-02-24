package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemLightningRodStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLightningRodStackInitializer {
  static void init() {
    ItemTypes.LIGHTNING_ROD_TYPE = ItemTypeBuilder
            .builder(ItemLightningRodStack.class)
            .vanillaItem(VanillaItemId.LIGHTNING_ROD)
            .build();
  }
}
