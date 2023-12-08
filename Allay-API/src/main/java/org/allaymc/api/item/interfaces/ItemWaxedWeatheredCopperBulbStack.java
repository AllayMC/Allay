package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaxedWeatheredCopperBulbStack extends ItemStack {
  ItemType<ItemWaxedWeatheredCopperBulbStack> WAXED_WEATHERED_COPPER_BULB_TYPE = ItemTypeBuilder
          .builder(ItemWaxedWeatheredCopperBulbStack.class)
          .vanillaItem(VanillaItemId.WAXED_WEATHERED_COPPER_BULB)
          .build();
}
