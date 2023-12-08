package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaxedCopperGrateStack extends ItemStack {
  ItemType<ItemWaxedCopperGrateStack> WAXED_COPPER_GRATE_TYPE = ItemTypeBuilder
          .builder(ItemWaxedCopperGrateStack.class)
          .vanillaItem(VanillaItemId.WAXED_COPPER_GRATE)
          .build();
}
