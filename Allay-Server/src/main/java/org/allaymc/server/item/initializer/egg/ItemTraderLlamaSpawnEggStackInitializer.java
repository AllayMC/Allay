package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemTraderLlamaSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTraderLlamaSpawnEggStackInitializer {
  static void init() {
    ItemTypes.TRADER_LLAMA_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemTraderLlamaSpawnEggStack.class)
            .vanillaItem(VanillaItemId.TRADER_LLAMA_SPAWN_EGG)
            .build();
  }
}
