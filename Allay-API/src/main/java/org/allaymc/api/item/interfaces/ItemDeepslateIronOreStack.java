package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDeepslateIronOreStack extends ItemStack {
  ItemType<ItemDeepslateIronOreStack> DEEPSLATE_IRON_ORE_TYPE = ItemTypeBuilder
          .builder(ItemDeepslateIronOreStack.class)
          .vanillaItem(VanillaItemId.DEEPSLATE_IRON_ORE)
          .build();
}
