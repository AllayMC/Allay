package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDeepslateGoldOreStack extends ItemStack {
  ItemType<ItemDeepslateGoldOreStack> DEEPSLATE_GOLD_ORE_TYPE = ItemTypeBuilder
          .builder(ItemDeepslateGoldOreStack.class)
          .vanillaItem(VanillaItemId.DEEPSLATE_GOLD_ORE)
          .build();
}
