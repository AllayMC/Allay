package org.allaymc.api.item.interfaces.copper;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaxedOxidizedCutCopperStack extends ItemStack {
  ItemType<ItemWaxedOxidizedCutCopperStack> WAXED_OXIDIZED_CUT_COPPER_TYPE = ItemTypeBuilder
          .builder(ItemWaxedOxidizedCutCopperStack.class)
          .vanillaItem(VanillaItemId.WAXED_OXIDIZED_CUT_COPPER)
          .build();
}
