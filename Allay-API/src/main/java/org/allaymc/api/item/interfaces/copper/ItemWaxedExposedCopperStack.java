package org.allaymc.api.item.interfaces.copper;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaxedExposedCopperStack extends ItemStack {
  ItemType<ItemWaxedExposedCopperStack> WAXED_EXPOSED_COPPER_TYPE = ItemTypeBuilder
          .builder(ItemWaxedExposedCopperStack.class)
          .vanillaItem(VanillaItemId.WAXED_EXPOSED_COPPER)
          .build();
}
