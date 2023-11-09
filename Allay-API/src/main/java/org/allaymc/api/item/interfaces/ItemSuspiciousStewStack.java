package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSuspiciousStewStack extends ItemStack {
  ItemType<ItemSuspiciousStewStack> SUSPICIOUS_STEW_TYPE = ItemTypeBuilder
          .builder(ItemSuspiciousStewStack.class)
          .vanillaItem(VanillaItemId.SUSPICIOUS_STEW)
          .build();
}
