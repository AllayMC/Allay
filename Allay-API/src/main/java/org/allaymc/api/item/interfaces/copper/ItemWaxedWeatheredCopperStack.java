package org.allaymc.api.item.interfaces.copper;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaxedWeatheredCopperStack extends ItemStack {
  ItemType<ItemWaxedWeatheredCopperStack> WAXED_WEATHERED_COPPER_TYPE = ItemTypeBuilder
          .builder(ItemWaxedWeatheredCopperStack.class)
          .vanillaItem(VanillaItemId.WAXED_WEATHERED_COPPER)
          .build();
}
