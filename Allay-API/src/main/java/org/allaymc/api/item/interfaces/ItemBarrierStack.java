package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBarrierStack extends ItemStack {
  ItemType<ItemBarrierStack> BARRIER_TYPE = ItemTypeBuilder
          .builder(ItemBarrierStack.class)
          .vanillaItem(VanillaItemId.BARRIER)
          .build();
}
