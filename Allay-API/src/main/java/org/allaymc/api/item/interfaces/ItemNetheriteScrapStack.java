package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemNetheriteScrapStack extends ItemStack {
  ItemType<ItemNetheriteScrapStack> NETHERITE_SCRAP_TYPE = ItemTypeBuilder
          .builder(ItemNetheriteScrapStack.class)
          .vanillaItem(VanillaItemId.NETHERITE_SCRAP)
          .build();
}
