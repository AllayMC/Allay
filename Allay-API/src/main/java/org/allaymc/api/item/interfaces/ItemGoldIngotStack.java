package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGoldIngotStack extends ItemStack {
  ItemType<ItemGoldIngotStack> GOLD_INGOT_TYPE = ItemTypeBuilder
          .builder(ItemGoldIngotStack.class)
          .vanillaItem(VanillaItemId.GOLD_INGOT)
          .build();
}
