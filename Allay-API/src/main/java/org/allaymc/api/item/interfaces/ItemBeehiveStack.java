package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBeehiveStack extends ItemStack {
  ItemType<ItemBeehiveStack> BEEHIVE_TYPE = ItemTypeBuilder
          .builder(ItemBeehiveStack.class)
          .vanillaItem(VanillaItemId.BEEHIVE)
          .build();
}
