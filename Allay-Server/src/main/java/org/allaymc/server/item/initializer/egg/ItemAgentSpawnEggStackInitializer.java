package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemAgentSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAgentSpawnEggStackInitializer {
  static void init() {
    ItemTypes.AGENT_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemAgentSpawnEggStack.class)
            .vanillaItem(VanillaItemId.AGENT_SPAWN_EGG)
            .build();
  }
}
