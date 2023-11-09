package org.allaymc.api.item.interfaces.copper;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemExposedCopperStack extends ItemStack {
  ItemType<ItemExposedCopperStack> EXPOSED_COPPER_TYPE = ItemTypeBuilder
          .builder(ItemExposedCopperStack.class)
          .vanillaItem(VanillaItemId.EXPOSED_COPPER)
          .build();
}
