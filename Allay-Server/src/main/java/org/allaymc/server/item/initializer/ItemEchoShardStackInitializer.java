package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemEchoShardStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemEchoShardStackInitializer {
  static void init() {
    ItemTypes.ECHO_SHARD_TYPE = ItemTypeBuilder
            .builder(ItemEchoShardStack.class)
            .vanillaItem(VanillaItemId.ECHO_SHARD)
            .build();
  }
}
