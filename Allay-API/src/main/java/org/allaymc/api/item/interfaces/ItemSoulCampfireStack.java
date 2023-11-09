package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSoulCampfireStack extends ItemStack {
  ItemType<ItemSoulCampfireStack> SOUL_CAMPFIRE_TYPE = ItemTypeBuilder
          .builder(ItemSoulCampfireStack.class)
          .vanillaItem(VanillaItemId.SOUL_CAMPFIRE)
          .build();
}
