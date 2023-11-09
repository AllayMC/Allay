package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDeepslateEmeraldOreStack extends ItemStack {
  ItemType<ItemDeepslateEmeraldOreStack> DEEPSLATE_EMERALD_ORE_TYPE = ItemTypeBuilder
          .builder(ItemDeepslateEmeraldOreStack.class)
          .vanillaItem(VanillaItemId.DEEPSLATE_EMERALD_ORE)
          .build();
}
