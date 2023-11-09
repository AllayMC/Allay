package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBarrelStack extends ItemStack {
  ItemType<ItemBarrelStack> BARREL_TYPE = ItemTypeBuilder
          .builder(ItemBarrelStack.class)
          .vanillaItem(VanillaItemId.BARREL)
          .build();
}
