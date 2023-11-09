package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlazeRodStack extends ItemStack {
  ItemType<ItemBlazeRodStack> BLAZE_ROD_TYPE = ItemTypeBuilder
          .builder(ItemBlazeRodStack.class)
          .vanillaItem(VanillaItemId.BLAZE_ROD)
          .build();
}
