package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSculkVeinStack extends ItemStack {
  ItemType<ItemSculkVeinStack> SCULK_VEIN_TYPE = ItemTypeBuilder
          .builder(ItemSculkVeinStack.class)
          .vanillaItem(VanillaItemId.SCULK_VEIN)
          .build();
}
