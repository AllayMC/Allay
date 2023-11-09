package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemObserverStack extends ItemStack {
  ItemType<ItemObserverStack> OBSERVER_TYPE = ItemTypeBuilder
          .builder(ItemObserverStack.class)
          .vanillaItem(VanillaItemId.OBSERVER)
          .build();
}
