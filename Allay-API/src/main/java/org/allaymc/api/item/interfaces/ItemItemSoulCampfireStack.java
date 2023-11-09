package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemSoulCampfireStack extends ItemStack {
  ItemType<ItemItemSoulCampfireStack> ITEM_SOUL_CAMPFIRE_TYPE = ItemTypeBuilder
          .builder(ItemItemSoulCampfireStack.class)
          .vanillaItem(VanillaItemId.ITEM_SOUL_CAMPFIRE)
          .build();
}
