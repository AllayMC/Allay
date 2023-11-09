package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAmethystClusterStack extends ItemStack {
  ItemType<ItemAmethystClusterStack> AMETHYST_CLUSTER_TYPE = ItemTypeBuilder
          .builder(ItemAmethystClusterStack.class)
          .vanillaItem(VanillaItemId.AMETHYST_CLUSTER)
          .build();
}
