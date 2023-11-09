package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBedStack extends ItemStack {
  ItemType<ItemBedStack> BED_TYPE = ItemTypeBuilder
          .builder(ItemBedStack.class)
          .vanillaItem(VanillaItemId.BED)
          .build();
}
