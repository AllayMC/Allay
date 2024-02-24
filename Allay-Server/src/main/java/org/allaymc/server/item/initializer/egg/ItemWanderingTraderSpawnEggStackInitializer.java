package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemWanderingTraderSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWanderingTraderSpawnEggStackInitializer {
  static void init() {
    ItemTypes.WANDERING_TRADER_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemWanderingTraderSpawnEggStack.class)
            .vanillaItem(VanillaItemId.WANDERING_TRADER_SPAWN_EGG)
            .build();
  }
}
