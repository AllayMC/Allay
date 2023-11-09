package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSuspiciousGravelStack extends ItemStack {
  ItemType<ItemSuspiciousGravelStack> SUSPICIOUS_GRAVEL_TYPE = ItemTypeBuilder
          .builder(ItemSuspiciousGravelStack.class)
          .vanillaItem(VanillaItemId.SUSPICIOUS_GRAVEL)
          .build();
}
