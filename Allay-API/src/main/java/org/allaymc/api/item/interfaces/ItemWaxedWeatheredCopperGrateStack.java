package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaxedWeatheredCopperGrateStack extends ItemStack {
  ItemType<ItemWaxedWeatheredCopperGrateStack> WAXED_WEATHERED_COPPER_GRATE_TYPE = ItemTypeBuilder
          .builder(ItemWaxedWeatheredCopperGrateStack.class)
          .vanillaItem(VanillaItemId.WAXED_WEATHERED_COPPER_GRATE)
          .build();
}
