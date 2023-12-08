package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemExposedCopperBulbStack extends ItemStack {
  ItemType<ItemExposedCopperBulbStack> EXPOSED_COPPER_BULB_TYPE = ItemTypeBuilder
          .builder(ItemExposedCopperBulbStack.class)
          .vanillaItem(VanillaItemId.EXPOSED_COPPER_BULB)
          .build();
}
