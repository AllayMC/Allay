package org.allaymc.api.item.interfaces.copper;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemOxidizedCutCopperStack extends ItemStack {
  ItemType<ItemOxidizedCutCopperStack> OXIDIZED_CUT_COPPER_TYPE = ItemTypeBuilder
          .builder(ItemOxidizedCutCopperStack.class)
          .vanillaItem(VanillaItemId.OXIDIZED_CUT_COPPER)
          .build();
}
