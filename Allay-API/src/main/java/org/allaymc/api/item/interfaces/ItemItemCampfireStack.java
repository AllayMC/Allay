package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemCampfireStack extends ItemStack {
  ItemType<ItemItemCampfireStack> ITEM_CAMPFIRE_TYPE = ItemTypeBuilder
          .builder(ItemItemCampfireStack.class)
          .vanillaItem(VanillaItemId.ITEM_CAMPFIRE)
          .build();
}
