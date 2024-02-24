package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemLlamaSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLlamaSpawnEggStackInitializer {
  static void init() {
    ItemTypes.LLAMA_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemLlamaSpawnEggStack.class)
            .vanillaItem(VanillaItemId.LLAMA_SPAWN_EGG)
            .build();
  }
}
