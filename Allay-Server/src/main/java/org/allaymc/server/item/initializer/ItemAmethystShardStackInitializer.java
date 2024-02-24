package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemAmethystShardStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAmethystShardStackInitializer {
  static void init() {
    ItemTypes.AMETHYST_SHARD_TYPE = ItemTypeBuilder
            .builder(ItemAmethystShardStack.class)
            .vanillaItem(VanillaItemId.AMETHYST_SHARD)
            .build();
  }
}
