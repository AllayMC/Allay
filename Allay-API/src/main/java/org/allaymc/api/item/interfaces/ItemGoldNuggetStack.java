package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGoldNuggetStack extends ItemStack {
  ItemType<ItemGoldNuggetStack> GOLD_NUGGET_TYPE = ItemTypeBuilder
          .builder(ItemGoldNuggetStack.class)
          .vanillaItem(VanillaItemId.GOLD_NUGGET)
          .build();
}
