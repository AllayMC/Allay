package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaxedCopperBulbStack extends ItemStack {
  ItemType<ItemWaxedCopperBulbStack> WAXED_COPPER_BULB_TYPE = ItemTypeBuilder
          .builder(ItemWaxedCopperBulbStack.class)
          .vanillaItem(VanillaItemId.WAXED_COPPER_BULB)
          .build();
}
