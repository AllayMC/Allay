package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemPrismarineShardStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPrismarineShardStackInitializer {
  static void init() {
    ItemTypes.PRISMARINE_SHARD_TYPE = ItemTypeBuilder
            .builder(ItemPrismarineShardStack.class)
            .vanillaItem(VanillaItemId.PRISMARINE_SHARD)
            .build();
  }
}
