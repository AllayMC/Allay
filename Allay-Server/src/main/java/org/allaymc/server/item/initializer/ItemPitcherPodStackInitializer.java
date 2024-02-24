package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemPitcherPodStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPitcherPodStackInitializer {
  static void init() {
    ItemTypes.PITCHER_POD_TYPE = ItemTypeBuilder
            .builder(ItemPitcherPodStack.class)
            .vanillaItem(VanillaItemId.PITCHER_POD)
            .build();
  }
}
