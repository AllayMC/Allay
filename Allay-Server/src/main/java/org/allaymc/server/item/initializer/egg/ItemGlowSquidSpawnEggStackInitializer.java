package org.allaymc.server.item.initializer.egg;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.egg.ItemGlowSquidSpawnEggStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGlowSquidSpawnEggStackInitializer {
  static void init() {
    ItemTypes.GLOW_SQUID_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemGlowSquidSpawnEggStack.class)
            .vanillaItem(VanillaItemId.GLOW_SQUID_SPAWN_EGG)
            .build();
  }
}
