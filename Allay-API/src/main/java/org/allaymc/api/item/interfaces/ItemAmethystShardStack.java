package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAmethystShardStack extends ItemStack {
  ItemType<ItemAmethystShardStack> AMETHYST_SHARD_TYPE = ItemTypeBuilder
          .builder(ItemAmethystShardStack.class)
          .vanillaItem(VanillaItemId.AMETHYST_SHARD)
          .build();
}
