package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlazePowderStack extends ItemStack {
  ItemType<ItemBlazePowderStack> BLAZE_POWDER_TYPE = ItemTypeBuilder
          .builder(ItemBlazePowderStack.class)
          .vanillaItem(VanillaItemId.BLAZE_POWDER)
          .build();
}
