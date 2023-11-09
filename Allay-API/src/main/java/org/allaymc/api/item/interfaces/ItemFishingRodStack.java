package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemFishingRodStack extends ItemStack {
  ItemType<ItemFishingRodStack> FISHING_ROD_TYPE = ItemTypeBuilder
          .builder(ItemFishingRodStack.class)
          .vanillaItem(VanillaItemId.FISHING_ROD)
          .build();
}
