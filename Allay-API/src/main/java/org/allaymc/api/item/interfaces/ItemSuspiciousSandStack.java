package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSuspiciousSandStack extends ItemStack {
  ItemType<ItemSuspiciousSandStack> SUSPICIOUS_SAND_TYPE = ItemTypeBuilder
          .builder(ItemSuspiciousSandStack.class)
          .vanillaItem(VanillaItemId.SUSPICIOUS_SAND)
          .build();
}
